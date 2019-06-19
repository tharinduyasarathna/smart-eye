package lk.tharindu.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Video implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date storedDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn
    private User user;

    @PrePersist
    protected void onCreate() {
        storedDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStoredDate() {
        return storedDate;
    }

    public void setStoredDate(Date storedDate) {
        this.storedDate = storedDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
