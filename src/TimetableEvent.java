import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Date;

public class TimetableEvent {

    private int dayOfWeek;
    private int startTime;
    private int endTime;
    private String id;
    private String eventInformation;
    private Date startDate;
    private Date endDate;

    /**
     * Constructor.
     * @param id                    A unique course ID
     * @param dayOfWeek             0 = Monday, 6 = Sunday
     * @param eventInformation      (String) Course Information
     * @param startDate             (Date) Course Start Date
     * @param endDate               (Date) Course End Date
     * @param startTime             (int) Course start time of day
     * @param endTime               (int) Course end time of day
     */
    public TimetableEvent(String id,
                          int dayOfWeek,
                          String eventInformation,
                          Date startDate,
                          Date endDate,
                          int startTime,
                          int endTime) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.eventInformation = eventInformation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor with no parameters, for returning empty courses
     */
    public TimetableEvent(){
        this.dayOfWeek = 7;
        this.startTime = 0;
        this.endTime = 0;
        this.id = "";
        this.eventInformation = "";
        this.startDate = new Date();
        this.endDate = new Date();
    };


    /**
     * Getter for course id
     * @return          (String) Course id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for Day of Week
     * 0 = Monday, 6 = Sunday
     * @return            (int) Day of Week
     */
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Setter for Day of Week
     * @param dayOfWeek
     */
    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Getter for Event Information
     * @return              (String) Information about the Course
     */
    public String getEventInformation() {
        return eventInformation;
    }

    /**
     * Setter for Event (course) Information
     * @param eventInformation
     */
    public void setEventInformation(String eventInformation) {
        this.eventInformation = eventInformation;
    }

    /**
     * Getter for Course Start Date
     * @return              (Date) Course Start Date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Setter for Course Start Date
     * @param startDate
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for Course End Date
     * @return          (Date) Course End Date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Setter for Course End Date
     * @param endDate
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for Course Start Time (time of day)
     * @return          (int) Time of Day (start)
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Setter for Course Start Time (time of day)
     * @param startTime
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Getter for Course End Time (time of day)
     * @return          (int) Time of Day (end)
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Setter for Course End Time (time of day)
     * @param endTime
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }
}
