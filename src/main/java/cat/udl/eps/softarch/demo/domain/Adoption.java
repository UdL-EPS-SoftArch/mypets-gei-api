package cat.udl.eps.softarch.demo.domain;

import java.util.Date;

public class Adoption extends UriEntity<Long> {
    private Long id;
    private String type;
    private Boolean confirmed;
    private Date startDate;
    private Date endDate;

    // Constructor
    public Adoption(Long id, String type, Boolean confirmed, Date startDate, Date endDate) {
        this.id = id;
        this.type = type;
        this.confirmed = confirmed;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    
    public Long getId() {
        return id;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
