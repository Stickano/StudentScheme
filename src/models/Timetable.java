package models;

import resources.CsvReader;
import resources.Parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Timetable {

    private List<TimetableEvent> events;
    public Location location;

    public Timetable(Location school) {
        this.events = new LinkedList<>();
        this.location = school;
    }

    /**
     * Returns all the events in a week
     * @return              All events
     */
    public List<TimetableEvent> GetWeekEvents(){
        return this.events;
    }

    /**
     * Return all the events on a certain day of the week
     * @param dayOfWeek     0 = Monday, 6 = Sunday
     * @return              The events for the selected day
     */
    public List<TimetableEvent> GetDayEvents(int dayOfWeek){
        List<TimetableEvent> dayEvents = new LinkedList<>();
        for (TimetableEvent event : events) {
            if (event.getDayOfWeek() == dayOfWeek)
                dayEvents.add(event);
        }

        return dayEvents;
    }

    /**
     * Adds a new event to the list of events
     * @param UniqueId      A uniquely identifiable class identification
     * @param dayOfWeek     0 = Monday, 6 = Sunday (unlikely, hopefully)
     * @param startDate     Starting date
     * @param endDate       Ending date
     * @param information   Course Information
     * @param startTime     Time of day (start)
     * @param endTime       Time of day (end)
     */
    public void AddEvent(String UniqueId,
                         Date startDate,
                         Date endDate,
                         String information,
                         int dayOfWeek,
                         int startTime,
                         int endTime) throws Exception{

        if (!CheckUniqueId(UniqueId))
            throw new Exception("ID is already in use");

        TimetableEvent newEvent = new TimetableEvent(UniqueId, dayOfWeek, information, startDate, endDate, startTime, endTime);
        events.add(newEvent);
    }

    /**
     * Will perform a check that the Course ID isn't already in use,
     * before creating a new course.
     * @param id    The id the match against
     * @return      Unique True/False
     */
    private boolean CheckUniqueId(String id){
        for (TimetableEvent event : events) {
            if (event.getId().equals(id))
                return false;
        }
        return true;
    }

    /**
     * Removes an event from the list of events
     * @param courseId      The unique id for the course
     */
    public void RemoveEvent(String courseId){
        for (TimetableEvent event : events) {
            if (event.getId().equals(courseId)) {
                events.remove(event);
                break;
            }
        }
    }

    /**
     * Get individual event
     * @param courseId      The id of the course
     * @return              The requested event,
     *                      or an empty event if id wasn't found
     */
    public TimetableEvent GetEvent(String courseId){
        for (TimetableEvent event : events) {
            if (event.getId().equals(courseId))
                return event;
        }
        return new TimetableEvent();
    }

    /**
     * Get the results from the events CSV and sort so only related school id is selected.
     * Then populate the list of events with those results.
     */
    private void ReadCsv() {
        CsvReader events = new CsvReader("src/csv/events.csv");
        for (String col[] : events.getResults()) {
            if (col[0].equals(location.GetId())){
                try {
                    // Start/End date
                    SimpleDateFormat dFormat = new SimpleDateFormat("yyyyMMdd");
                    Date startDate = dFormat.parse(col[4]);
                    Date endDate = dFormat.parse(col[5]);

                    // Parse ints (start/end time & day of week)
                    int startTime = -1;
                    int endTime = -1;
                    int dow = -1;
                    if (Parser.isInt(col[6]))
                        startTime = Integer.parseInt(col[6]);
                    if (Parser.isInt(col[7]))
                        endTime = Integer.parseInt(col[7]);
                    if (Parser.isInt(col[2]))
                        dow = Integer.parseInt(col[2]);

                    this.events.add(new TimetableEvent(col[1], dow, col[3], startDate, endDate, startTime, endTime));

                } catch(Exception e) { System.out.printf(e.getMessage()); }
            }
        }


    }
}
