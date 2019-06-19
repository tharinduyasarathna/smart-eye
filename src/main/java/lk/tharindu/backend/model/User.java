package lk.tharindu.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Size(min=5, max=14, message="Your name should be between 5 - 14 characters.")
    private String name;
    private String password;

    @Email(message = "Please enter valid email address")
    private String email;
    private String phone;
    private Byte[] photo;
    private String userType;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Report> report;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Camera> camera;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Image> image;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Video> video;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Report> getReport() {
        return report;
    }

    public void setReport(List<Report> report) {
        this.report = report;
    }

    public List<Camera> getCamera() {
        return camera;
    }

    public void setCamera(List<Camera> camera) {
        this.camera = camera;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public List<Video> getVideo() {
        return video;
    }

    public void setVideo(List<Video> video) {
        this.video = video;
    }
}
