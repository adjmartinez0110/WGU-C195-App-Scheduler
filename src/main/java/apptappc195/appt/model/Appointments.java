package apptappc195.appt.model;

import java.time.LocalDateTime;
import java.time.LocalTime;


public class Appointments {
    private int apptId;
    private int custId;
    private int userId;
    private String title;
    private String description;
    private String location;
    private int contactId;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructor(s)
     * @param custId
     * @param userId
     * @param title
     * @param description
     * @param location
     * @param contactId
     * @param type
     * @param startTime
     * @param endTime
     */
    public Appointments(int apptId, int custId, int userId, String title, String description, String location, int contactId, String type, LocalDateTime startTime, LocalDateTime endTime) {
        this.apptId = apptId;
        this.custId = custId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contactId = contactId;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Appointments(String type) {
        this.type = type;
    }


    //Getters and Setters

    public int getApptId() {
        return apptId;
    }
    public void setApptId(int apptId) {
        this.apptId = apptId;
    }
    public int getCustId() {
        return custId;
    }
    public void setCustId(int custId) {
        this.custId = custId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public int getContactId() {
        return contactId;
    }
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime toLocalTime() {

        return null;
    }

    public String toString() {

        return String.valueOf(type);
    }
}