package group1.tutoringcenter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tutors")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Column(nullable = false)
    @Min(value = 0, message = "Experience must be non-negative")
    private int experience;

    @Column(length = 500)
    private String bio;

    @Transient
    private List<Course> courses_taught = new ArrayList<>();

    public Tutor() {
    }

    public Tutor(String name, String email, int experience, String bio) {
        this.name = name;
        this.email = email;
        this.experience = experience;
        this.bio = bio;
    }

    public Tutor(int id, String name, String email, String password, int experience, String bio) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.experience = experience;
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Course> getCourses_taught() {
        return courses_taught;
    }

    public void setCourses_taught(List<Course> courses_taught) {
        this.courses_taught = courses_taught;
    }

    public void assignCourse(Course course) {
        courses_taught.add(course);
    }
}