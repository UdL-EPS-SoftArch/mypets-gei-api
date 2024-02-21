package cat.udl.eps.softarch.demo.domain;

import java.util.Date;

public class MedicalRecord extends UriEntity<Long> {
    private final Long id;
    private String issue;
    private String description;
    private Date date;
    
    

    // Constructor
    public MedicalRecord(Long id, String issue, String description, Date date) {
        this.id = id;
        this.issue = issue;
        this.description = description;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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