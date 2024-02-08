package apptappc195.appt.model;

import java.time.LocalDateTime;

public class Reports {

    private String type;
    private String month_name;
    private int total;
    private int apptId;
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int custId;
    private int contactId;
    private String location;



    /**
     * Constructor for type and month report
     */
    public Reports(String type, String month_name, int total) {
        this.type = type;
        this.month_name = month_name;
        this.total = total;
    }

    /**
     * Constructor for contact report
     */
    public Reports(int apptId, String title, String type, String description, LocalDateTime startTime, LocalDateTime endTime, int custId) {
        this.apptId = apptId;
        this.title = title;
        this.type = type;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.custId = custId;
    }

    /**
     * Constructor for additional report
     */
    public Reports(String location, int total) {
        this.location = location;
        this.total = total;
    }




    /**
     * Getters and Setters
     * @return
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
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

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getMonth_name() {
        return month_name;
    }

    public void setMonth_name(String month_name) {
        this.month_name = month_name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String toString() {

        return String.valueOf(contactId);
    }
}
