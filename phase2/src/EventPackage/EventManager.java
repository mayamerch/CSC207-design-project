package EventPackage;

import java.util.ArrayList;
import java.util.Date;

public class EventManager {

    private ArrayList<Event> eventList;
    private ArrayList<Party> partyList;
    private ArrayList<SingleSpeaker> singleSpeakerList;
    private ArrayList<MultiSpeaker> multiSpeakerList;
    private int nextID;



    /**
     * Creates an instance eventManager that contains all the events in eventList
     * @param eventList The list of events this instance of eventManager should store
     */
    public EventManager(ArrayList<Event> eventList) {
        this.eventList = eventList;
        if (eventList.size() == 0) {
            this.nextID = 1;
        } else {
            this.nextID = eventList.get(eventList.size() - 1).getEventId() + 1;
        }
    }


    /**
     * Creates a new Party and stores it in eventManager and increments eventCounter if the event is happening in an
     * available room and at an available time. Returns -1 if it wasn't created and returns the ID of the event created
     * if its successful
     * @param eventName Name of event to be made
     * @param eventCapacity The capacity of the event
     * @param eventDate The date of the event
     * @param eventRoom The id of the room this event takes place
     * @param eventDuration The duration of the event in hours
     * @param VIPStatus Whether this event is VIP exclusive or not
     * @return -1 if event wasn't created and the ID of the event if it was created
     */
    public int createParty(String eventName, int eventCapacity, Date eventDate, int eventRoom,
                           int eventDuration, boolean VIPStatus) {
        if (roomCompare(nextID, eventRoom, eventDate, eventDuration))
            return -1;
        Party newEvent = new Party(nextID, eventName, eventCapacity, eventDate,
                eventRoom, eventDuration, VIPStatus);
        eventList.add(newEvent);
        partyList.add(newEvent);
        nextID += 1;
        return nextID - 1;
    }


    /**
     * Creates a new SingleSpeaker event and stores it in eventManager and increments eventCounter if the event is happening in an
     * available room and at an available time with an available speaker. Returns -1 if it wasn't created and returns the ID of the event created
     * if its successful
     * @param eventName Name of event to be made
     * @param eventCapacity The capacity of the event
     * @param eventDate The date of the event
     * @param eventRoom The id of the room this event takes place
     * @param eventDuration The duration of the event in hours
     * @param VIPStatus Whether this event is VIP exclusive or not
     * @param eventSpeaker The id of the speaker at this event
     * @return -1 if event wasn't created and the ID of the event if it was created
     */
    public int createSingleSpeaker(String eventName, int eventCapacity, Date eventDate, int eventRoom,
                                   int eventDuration, boolean VIPStatus, int eventSpeaker) {
        if (roomCompare(nextID, eventRoom, eventDate, eventDuration))
            return -1;

        if(speakerCompare(nextID, eventDate, eventDuration, eventSpeaker))
            return -1;

        SingleSpeaker newEvent = new SingleSpeaker(nextID, eventName, eventCapacity, eventDate,
                eventRoom, eventDuration, VIPStatus, eventSpeaker);
        eventList.add(newEvent);
        singleSpeakerList.add(newEvent);
        nextID += 1;
        return nextID - 1;
    }




    /**
     * Creates a new MultiSpeaker event and stores it in eventManager and increments eventCounter if the event is happening in an
     * available room and at an available time with available speakers. Returns -1 if it wasn't created and returns the ID of the event created
     * if its successful
     * @param eventName Name of event to be made
     * @param eventCapacity The capacity of the event
     * @param eventDate The date of the event
     * @param eventRoom The id of the room this event takes place
     * @param eventDuration The duration of the event in hours
     * @param VIPStatus Whether this event is VIP exclusive or not
     * @param eventSpeakers The ids of the speakers at this event
     * @return -1 if event wasn't created and the ID of the event if it was created
     */
    public int createMultiSpeaker(String eventName, int eventCapacity, Date eventDate, int eventRoom,
                                   int eventDuration, boolean VIPStatus, ArrayList<Integer> eventSpeakers) {
        if (roomCompare(nextID, eventRoom, eventDate, eventDuration))
            return -1;

        for (Integer speakerId: eventSpeakers)
            if (speakerCompare(nextID, eventDate, eventDuration, speakerId))
                return -1;

        MultiSpeaker newEvent = new MultiSpeaker(nextID, eventName, eventCapacity, eventDate,
                eventRoom, eventDuration, VIPStatus, eventSpeakers);
        eventList.add(newEvent);
        multiSpeakerList.add(newEvent);
        nextID += 1;
        return nextID - 1;
    }




    private boolean speakerCompare(int eventId, Date eventDate, int eventDuration, int eventSpeaker) {
        for (SingleSpeaker event: singleSpeakerList) {
            if (event.getEventId() != eventId &&event.getEventSpeaker() == eventSpeaker)
                if (!dateCompare(eventDate, event.getEventDate(), eventDuration, event.getEventDuration())) {
                    return true;
                }
        }

        for (MultiSpeaker event: multiSpeakerList) {
            if (event.getEventId() != eventId && event.getEventSpeakers().contains(eventSpeaker))
                if (!dateCompare(eventDate, event.getEventDate(), eventDuration, event.getEventDuration())) {
                    return true;
                }
        }
        return false;
    }



    private boolean roomCompare(int eventId, int eventRoom, Date eventDate, int eventDuration) {
        for (Event event : eventList) {
            if (event.getEventId() != eventId && event.getEventRoom() == (eventRoom))
                if (!dateCompare(eventDate, event.getEventDate(), eventDuration, event.getEventDuration())) {
                    return true;
                }
        }
        return false;
    }


    // true if dates don't overlap false if they do
    private boolean dateCompare(Date date1, Date date2, int duration1, int duration2) {
        if (date1.getYear() != date2.getYear())
            return true;
        else if (date1.getMonth() != date2.getMonth())
            return true;
        else if (date1.getDate() != date2.getDate())
            return true;


        if (date1.getHours() > date2.getHours()) {
            if (date2.getHours() + duration2 > date1.getHours())
                return false;

            if (date2.getHours() + duration2 == date1.getHours())
                return date1.getMinutes() >= date2.getMinutes();

            return true;
        }


        if (date1.getHours() < date2.getHours()) {
            if (date1.getHours() + duration1 > date2.getHours())
                return false;

            if (date1.getHours() + duration1 == date2.getHours())
                return date1.getMinutes() <= date2.getMinutes();

            return true;
        }

        return false;
    }


    /**
     * Enrolls current user to event with eventID and returns an integer
     * @param eventID: ID of an event
     * @param userID ID of a user
     * @return 1, if enrollement was successful <p></p>
     *         0, if UID is already enrolled or event is full <p></p>
     *         -1, if event does not exist <p></p>
     */
    public int enroll(int eventID, int userID) {
        for (Event event: this.eventList) {
            if (event.getEventId() == eventID) {
                if(!event.enrolled(userID)) {
                    event.addAttendee(userID);
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }


    /**
     * Removes registration of current user to event with eventID and returns an integer
     * @param eventID: ID of an event
     * @param userID ID of a user
     * @return 1, if unenrollement was successful <p></p>
     *         0, if UID is not enrolled <p></p>
     *         -1, if event does not exist <p></p>
     */
    public int unenroll(int eventID, int userID) {
        for (Event event: this.eventList) {
            if (event.getEventId() == eventID) {
                if(event.enrolled(userID)) {
                    event.removeAttendee(userID);
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }

    /**
     * Returns a list of events that the user can sign up for
     * @param userID ID of a user
     * @return Returns ArrayList of Events user can sign up for.
     */
    public ArrayList<Event> availEvents(int userID) {
        ArrayList<Event> availEventList = new ArrayList<Event>();
        for (Event event: this.eventList) {
            if (!event.enrolled(userID)){
                availEventList.add(event);
            }
        }
        return availEventList;
    }


    /**
     * Returns list of events that user has signed up for
     * @param userID ID of a user
     * @return Returns ArrayList of Events that user has signed up for.
     */
    public ArrayList<Event> myEvents(int userID) {
        ArrayList<Event> enrolledEvents = new ArrayList<Event>();
        for (Event event: this.eventList) {
            if (event.enrolled(userID)){
                enrolledEvents.add(event);
            }
        }
        return enrolledEvents;
    }


    /**
     * Returns an event corresponding to a specific eventID
     * @param eventID The ID of an event to be returned
     * @return        The event corresponding to the eventID. Throws an exception if the event doesn't exist.
     */
    public Event getEvent(int eventID){
        for (Event event: eventList) {
            if (event.getEventId() == eventID)
                return event;
        }
        throw new ArrayIndexOutOfBoundsException("This Event doesn't exist yet.");
    }


    /**
     * Checks if an event is included in the list of events and removes it if it does. Returns true if it was removed
     * and false if not found.
     * @param eventID The ID of the event to be deleted
     * @return        True if event was deleted, false if not found
     */
    public boolean cancelEvent(int eventID) {
        for (int i = 0; i < eventList.size(); i++) {
            if (eventList.get(i).getEventId() == eventID) {
                eventList.remove(i);
                return true;
            }
        }
        return false;
    }


    /**
     * Reschedules a Party by changing its Date, Capacity, Room, and Duration (not included in phase 1) as long as its
     * possible.
     * @param eventId The id of the vent to be rescheduled
     * @param eventCapacity The capacity of the event
     * @param eventDate The new date of event
     * @param eventRoom The new room of event
     * @param eventDuration The new duration of th event
     * @return              True if event was rescheduled, false if it was unable to
     */
    public boolean rescheduleParty(int eventId, int eventCapacity, Date eventDate, int eventRoom,
                              int eventDuration) {
        int index = -1;
        for (int i = 0; i < partyList.size(); i++) {
            if (partyList.get(i).getEventId() == eventId)
                index = i;
        }

        if (index == -1)
            return false;

        if (roomCompare(eventId, eventRoom, eventDate, eventDuration))
            return false;


        partyList.get(index).setEventDate(eventDate);
        partyList.get(index).setEventRoom(eventRoom);
        partyList.get(index).setEventDuration(eventDuration);
        partyList.get(index).setEventCapacity(eventCapacity);

        return true;
    }


    /**
     * Reschedules a Party by changing its Date, Capacity, Room, and Duration (not included in phase 1) as long as its
     * possible.
     * @param eventId The id of the vent to be rescheduled
     * @param eventCapacity The capacity of the event
     * @param eventDate The new date of event
     * @param eventRoom The new room of event
     * @param eventDuration The new duration of th event
     * @param speakerId The id of new speaker  at this event
     * @return              True if event was rescheduled, false if it was unable to
     */
    public boolean rescheduleSingleSpeaker(int eventId, int eventCapacity, Date eventDate, int eventRoom,
                                   int eventDuration, int speakerId) {
        int index = -1;
        for (int i = 0; i < singleSpeakerList.size(); i++) {
            if (singleSpeakerList.get(i).getEventId() == eventId)
                index = i;
        }

        if (index == -1)
            return false;

        if (roomCompare(eventId, eventRoom, eventDate, eventDuration)
                || speakerCompare(eventId, eventDate, eventDuration, speakerId))
            return false;


        singleSpeakerList.get(index).setEventDate(eventDate);
        singleSpeakerList.get(index).setEventRoom(eventRoom);
        singleSpeakerList.get(index).setEventDuration(eventDuration);
        singleSpeakerList.get(index).setEventCapacity(eventCapacity);
        singleSpeakerList.get(index).setEventSpeaker(speakerId);

        return true;
    }


    /**
     * Reschedules a Party by changing its Date, Capacity, Room, and Duration (not included in phase 1) as long as its
     * possible.
     * @param eventId The id of the vent to be rescheduled
     * @param eventCapacity The capacity of the event
     * @param eventDate The new date of event
     * @param eventRoom The new room of event
     * @param eventDuration The new duration of the event
     * @param speakerIds The ids of  the new speaker of this event
     * @return              True if event was rescheduled, false if it was unable to
     */
    public boolean rescheduleMultiSpeaker(int eventId, int eventCapacity, Date eventDate, int eventRoom,
                                   int eventDuration, ArrayList<Integer> speakerIds) {
        int index = -1;
        for (int i = 0; i < multiSpeakerList.size(); i++) {
            if (multiSpeakerList.get(i).getEventId() == eventId)
                index = i;
        }

        if (index == -1)
            return false;

        if (roomCompare(eventId, eventRoom, eventDate, eventDuration))
            return false;

        if (speakerIds.size() == 0)
            return false;

        for (Integer speakerId: speakerIds)
            if (speakerCompare(eventId, eventDate, eventDuration, speakerId))
                return false;

        multiSpeakerList.get(index).setEventDate(eventDate);
        multiSpeakerList.get(index).setEventRoom(eventRoom);
        multiSpeakerList.get(index).setEventDuration(eventDuration);
        multiSpeakerList.get(index).setEventCapacity(eventCapacity);
        multiSpeakerList.get(index).setEventSpeakers(speakerIds);

        return true;
    }


    /**
     * Returns the list of events in this EventManger
     * @return      List of events
     */
    public ArrayList<Event> getEventList() {
        return eventList;
    }

    /**
     * Returns the list of parties in this EventManger
     * @return      List of parties
     */
    public ArrayList<Party> getPartyList() {
        return partyList;
    }

    /**
     * Returns the list of Single Speaker events in this EventManger
     * @return      List of Single Speaker events
     */
    public ArrayList<SingleSpeaker> getSingleSpeakerList() {
        return singleSpeakerList;
    }

    /**
     * Returns the list of Multi Speaker events in this EventManger
     * @return      List of Multi Speaker events
     */
    public ArrayList<MultiSpeaker> getMultiSpeakerList() {
        return multiSpeakerList;
    }


    /**
     * Returns the list of events a specific speaker is speaking at
     * @param SpeakerId The Id of the speaker
     * @return          The Events the speaker is speaking at
     */
    public ArrayList<Event> speakingAt(int SpeakerId) {
        ArrayList<Event> speaking = new ArrayList<Event>();

        for (SingleSpeaker event: singleSpeakerList) {
            if (event.getEventSpeaker() == SpeakerId){
                speaking.add(event);
            }
        }

        for (MultiSpeaker event: multiSpeakerList) {
            if (event.getEventSpeakers().contains(SpeakerId)){
                speaking.add(event);
            }
        }

        return speaking;
    }


    /**
     * Returns a list of ids of all the speakers currently speaking at any event
     * @return a list of ids of all the speakers
     */
    public ArrayList<Integer> getAllSpeakers() {
        ArrayList<Integer> allSpeakers = new ArrayList<>();
        for (SingleSpeaker event: singleSpeakerList) {
            if (!allSpeakers.contains(event.getEventSpeaker()))
                allSpeakers.add(event.getEventSpeaker());
        }

        for (MultiSpeaker event: multiSpeakerList) {
            for (int speakerId: event.getEventSpeakers())
                if (!allSpeakers.contains(speakerId))
                    allSpeakers.add(speakerId);
        }

        return allSpeakers;
    }


    /**
     * Returns a list of ids of all the attendees currently attending any event
     * @return a list of ids of all the attendees
     */
    public ArrayList<Integer> getAllAttendees() {
        ArrayList<Integer> allAttendees = new ArrayList<>();
        for (Event event: eventList)
            for (Integer attendee: event.getEventAttendees())
                if (!allAttendees.contains(attendee))
                    allAttendees.add(attendee);
        return allAttendees;
    }


    /**
     * Returns a list of ids of all users currently involved in an event
     * @return a list of all the users involved in an event
     */
    public ArrayList<Integer> getAllParticipants() {
        ArrayList<Integer> allParticipants = new ArrayList<>();
        for (Event event: eventList)
            for (Integer participant: event.getParticipants())
                if (!allParticipants.contains(participant))
                    allParticipants.add(participant);
        return allParticipants;
    }
}