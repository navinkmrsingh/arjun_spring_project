-- Create Database if it does not exist
CREATE DATABASE IF NOT EXISTS `iste240`;
USE `iste240`;

--  Create Tutors Table
CREATE TABLE IF NOT EXISTS `tutors` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(100) NOT NULL,
    `experience` INT NOT NULL DEFAULT 0,
    `bio` VARCHAR(500)
);

--  Create Admin Table
CREATE TABLE IF NOT EXISTS `admin` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    `email` VARCHAR(255),
    `department` VARCHAR(255)
);

--  Create Student Table
CREATE TABLE IF NOT EXISTS `student` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255),
    `email` VARCHAR(255),
    `enrolled_course` VARCHAR(255)
);

--  Create Courses Table (Even if currently a POJO, good to have)
CREATE TABLE IF NOT EXISTS `courses` (
    `course_id` INT AUTO_INCREMENT PRIMARY KEY,
    `course_name` VARCHAR(100) NOT NULL,
    `course_code` VARCHAR(20) NOT NULL UNIQUE,
    `description` TEXT,
    `credits` INT NOT NULL,
    `department` VARCHAR(50),
    `tutor_name` VARCHAR(255)
);

-- Insert Initial Mock Tutors
INSERT IGNORE INTO `tutors` (`id`, `name`, `email`, `password`, `experience`, `bio`) VALUES
(1, 'Ivan Ivanov', 'demo1@abd.com', 'password', 1, 'EMPTY'),
(2, 'Aimen Jafrani', 'demo2@abd.com', 'password', 2, 'EMPTY');

-- Insert Initial Mock Students
INSERT IGNORE INTO `student` (`id`, `name`, `email`, `enrolled_course`) VALUES
(1, 'Safiia Musaeva', 'demo3@abcd.com', 'Web Development I');

-- Insert Initial Mock Courses
INSERT IGNORE INTO `courses` (`course_id`, `course_name`, `course_code`, `description`, `credits`, `department`, `tutor_name`) VALUES
(1, 'Web and Mobile Development II', 'ISTE240', 'This course builds on the basics of web page development that are presented in Web and Mobile I and extends that knowledge to focus on theories, issues, and technologies related to the design and development of web sites.', 3, 'Computing', 'Abdullah'),
(2, 'Calculus I', 'MATH181', 'The course covers functions, limits, continuity, the derivative, rules of differentiation, applications of the derivative, Riemann sums, definite integrals, and indefinite integrals.', 3, 'Mathematics', 'Mariyam'),
(3, 'Web and Mobile Development I', 'ISTE140', 'This course provides students with an introduction to internet and web technologies, and to development on Macintosh/UNIX computer platforms. Topics include HTML and CSS, CSS3 features, digital images, web page design and website publishing.', 3, 'Computing', 'Leen');
