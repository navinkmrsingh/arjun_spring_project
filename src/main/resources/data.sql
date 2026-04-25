-- Insert Initial Mock Tutors
INSERT IGNORE INTO `tutors` (`id`, `name`, `email`, `password`, `experience`, `bio`) VALUES
(1, 'Ivan Ivanov', 'demo1@abd.com', 'password', 1, 'EMPTY'),
(2, 'Aimen Jafrani', 'demo2@abd.com', 'password', 2, 'EMPTY');

-- Insert Initial Mock Admins
INSERT IGNORE INTO `admin` (`id`, `name`, `email`, `department`) VALUES
(1, 'Hans Orsted', 'admin1@tutoring.com', 'Academics'),
(2, 'Nikola Tesla', 'admin2@tutoring.com', 'Operations');

-- Insert Initial Mock Students
INSERT IGNORE INTO `student` (`id`, `name`, `email`, `enrolled_course`) VALUES
(1, 'Safiia Musaeva', 'demo3@abcd.com', 'Web Development I');

-- Insert Initial Mock Courses
INSERT IGNORE INTO `courses` (`course_id`, `course_name`, `course_code`, `description`, `credits`, `department`, `tutor_id`) VALUES
(1, 'Web and Mobile Development II', 'ISTE240', 'This course builds on the basics of web page development that are presented in Web and Mobile I and extends that knowledge to focus on theories, issues, and technologies related to the design and development of web sites.', 3, 'Computing', 1),
(2, 'Calculus I', 'MATH181', 'The course covers functions, limits, continuity, the derivative, rules of differentiation, applications of the derivative, Riemann sums, definite integrals, and indefinite integrals.', 3, 'Mathematics', 2),
(3, 'Web and Mobile Development I', 'ISTE140', 'This course provides students with an introduction to internet and web technologies, and to development on Macintosh/UNIX computer platforms. Topics include HTML and CSS, CSS3 features, digital images, web page design and website publishing.', 3, 'Computing', 1);


