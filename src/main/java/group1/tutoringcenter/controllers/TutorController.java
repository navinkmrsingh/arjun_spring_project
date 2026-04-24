/**  
 * Arjun Gupta
 * UID: 415003018
 * Entity: Tutor
 * ISTE 240 - Group 1
*/

package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Tutor;
import group1.tutoringcenter.models.Course;
import group1.tutoringcenter.services.CourseService;
import group1.tutoringcenter.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class TutorController {

    private TutorService tutorService;
    private CourseService courseService;

    @Autowired
    public TutorController(TutorService tutorService, CourseService courseService) {
        this.tutorService = tutorService;
        this.courseService = courseService;
    }

    @GetMapping("/tutors")
    public String tutors(Model model) {

        var tutor_list = this.tutorService.findAll();

        model.addAttribute("tutor_list", tutor_list);
        model.addAttribute("course_list", courseService.getCourses());

        return "tutors";
    }

    @PostMapping("/tutors")
    public String addTutor(Tutor tutor, @RequestParam(value = "courseIds", required = false) List<Integer> courseIds) {
        if (courseIds != null) {
            for (Integer id : courseIds) {
                Course course = getCourseById(id);
                if (course != null) {
                    tutor.assignCourse(course);
                }
            }
        }
        this.tutorService.addTutor(tutor);
        return "redirect:/add/success/Tutor";
    }

    private Course getCourseById(int id) {
        for (Course course : courseService.getCourses()) {
            if (course.getCourseId() == id) {
                return course;
            }
        }
        return null;
    }

    @GetMapping("/tutors/{id}")
    public String tutorProfile(@PathVariable("id") int id, Model model) {
        Tutor tutor = tutorService.getTutorById(id);
        if (tutor != null) {
            model.addAttribute("tutor", tutor);
            return "tutor_profile";
        }
        return "redirect:/tutors";
    }

    @GetMapping("/tutors/add")
    public String addtutor(Model model) {
        model.addAttribute("course_list", courseService.getCourses());
        return "tutors_form";
    }

}