package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Course;
import group1.tutoringcenter.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String viewCourses(Model model) {
        model.addAttribute("courses",courseService.getCourses());
        return "courses";
    }

    @GetMapping("/courses/add")
    public String showAddCourseForm() {

        return "courses_form";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute Course course) {
        courseService.addCourse(course);
        return "redirect:/add/success/course";
    }

}
