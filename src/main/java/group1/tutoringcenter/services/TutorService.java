/**  
 * Arjun Gupta
 * UID: 415003018
 * Entity: Tutor
 * ISTE 240 - Group 1
*/

package group1.tutoringcenter.services;

import group1.tutoringcenter.models.Course;
import group1.tutoringcenter.models.Tutor;
import group1.tutoringcenter.repositories.TutorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TutorService {

    private final TutorRepository tutorRepository;
    private final CourseService courseService;

    @Autowired
    public TutorService(TutorRepository tutorRepository, CourseService courseService) {
        this.tutorRepository = tutorRepository;
        this.courseService = courseService;
    }

    @PostConstruct
    public void initData() {
        // Populate the database with default tutors only if it's empty
        if (tutorRepository.count() == 0) {
            Tutor t1 = new Tutor();
            t1.setName("Ivan Ivanov");
            t1.setEmail("demo1@abd.com");
            t1.setPassword("password");
            t1.setExperience(1);
            t1.setBio("EMPTY");

            Tutor t2 = new Tutor();
            t2.setName("Aimen Jafrani");
            t2.setEmail("demo2@abd.com");
            t2.setPassword("password");
            t2.setExperience(2);
            t2.setBio("EMPTY");

            for (Course course : courseService.getCourses()) {
                if (course.getCourseId() == 1) {
                    t1.assignCourse(course);
                } else if (course.getCourseId() == 2 || course.getCourseId() == 3) {
                    t2.assignCourse(course);
                }
            }

            tutorRepository.save(t1);
            tutorRepository.save(t2);
        }
    }

    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    public Tutor getTutorById(int id) {
        return tutorRepository.findById(id).orElse(null);
    }

    public Tutor addTutor(Tutor tutor) {
        // Fetch managed Course entities from DB
        if (tutor.getCourses_taught() != null) {
            List<Course> managedCourses = new ArrayList<>(tutor.getCourses_taught().stream()
                .map(c -> courseService.getCourseById(c.getCourseId()).orElse(null))
                .filter(c -> c != null)
                .toList());
            tutor.setCourses_taught(managedCourses);
        }
        return tutorRepository.save(tutor);
    }

    public Tutor updateTutor(int id, Tutor tutor) {
        Optional<Tutor> existing = tutorRepository.findById(id);
        if (existing.isPresent()) {
            Tutor t = existing.get();
            t.setName(tutor.getName());
            t.setEmail(tutor.getEmail());
            if (tutor.getPassword() != null && !tutor.getPassword().isEmpty()) {
                t.setPassword(tutor.getPassword());
            }
            t.setExperience(tutor.getExperience());
            t.setBio(tutor.getBio());
            
            // Update Many-to-Many relationship with managed entities
            if (tutor.getCourses_taught() != null) {
                List<Course> managedCourses = new ArrayList<>(tutor.getCourses_taught().stream()
                    .map(c -> courseService.getCourseById(c.getCourseId()).orElse(null))
                    .filter(c -> c != null)
                    .toList());
                t.setCourses_taught(managedCourses);
            } else {
                t.setCourses_taught(new ArrayList<>());
            }
            
            return tutorRepository.save(t);
        }
        return null;
    }

    public void deleteTutor(int id) {
        tutorRepository.deleteById(id);
    }

    public List<Tutor> searchByName(String name) {
        return tutorRepository.findByNameContainingIgnoreCase(name);
    }
}