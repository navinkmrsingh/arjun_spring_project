package group1.tutoringcenter.services;

import group1.tutoringcenter.models.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutoringCenterService {

    private List<Tutor> tutors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();
    private List<Admin> admins = new ArrayList<>();
    private List<Request> requests = new ArrayList<>();

    public TutoringCenterService() {

        tutors.add(new Tutor("Ivan Ivanov", "demo1@abd.com",1,"EMPTY"));
        tutors.add(new Tutor("Aimen Jafrani", "demo2@abd.com",2,"EMPTY"));

        // test student
        students.add(new Student("demo3@abcd.com","Safiia Musaeva", "Web Development I"));

        requests.add(new Request(1, "Ali", "PENDING"));
        requests.add(new Request(2, "Sara", "ACCEPTED"));
    }

    public List<Tutor> getAllTutors() { return tutors; }
    public List<Student> getAllStudents() { return students; }

    public void addTutor(Tutor tutor) { tutors.add(tutor); }
    public void addStudent(Student student) { students.add(student); }

    public List<Request> getRequests() { return requests; }
    public void addRequest(Request request) { requests.add(request); }

}
