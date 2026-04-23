package group1.tutoringcenter.services;

import group1.tutoringcenter.models.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private List<Course> courses = new ArrayList<>();

    public CourseService(){
        courses.add(new Course(1, "Web and Mobile Development II", "ISTE240",
                "This course builds on the basics of web page development that are presented in Web and Mobile I and extends that knowledge to focus on theories, issues, and technologies related to the design and development of web sites.",
                3, "Computing", "Abdullah"));

        courses.add(new Course(2, "Calculus I", "MATH181",
                "The course covers functions, limits, continuity, the derivative, rules of differentiation, applications of the derivative, Riemann sums, definite integrals, and indefinite integrals.",
                3, "Mathematics", "Mariyam"));

        courses.add(new Course(3, "Web and Mobile Development I", "ISTE140",
                "This course provides students with an introduction to internet and web technologies, and to development on Macintosh/UNIX computer platforms. Topics include HTML and CSS, CSS3 features, digital images, web page design and website publishing.",
                3, "Computing", "Leen"));
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course){
        course.setCourseId(courses.size() + 1);
        courses.add(course);
    }
}
