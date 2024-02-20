package cat.udl.eps.softarch.demo.domain;

import java.util.Date;

public class MedicalRecord {
    private Integer id;
    private String issue;
    private String description;
    private Date date;

    // Constructor
    public MedicalRecord(Integer id, String issue, String description, Date date) {
        this.id = id;
        this.issue = issue;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}