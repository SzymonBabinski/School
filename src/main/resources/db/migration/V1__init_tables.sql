DROP TABLE IF EXISTS classes, grades, lessons, students, teachers;
CREATE TABLE teachers
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name  VARCHAR(50)
);

CREATE TABLE classes
(
    class_name VARCHAR(50) PRIMARY KEY,
    teacher_id INT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);

CREATE TABLE students
(
    id                 INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name         VARCHAR(50),
    last_name          VARCHAR(50),
    student_class_name VARCHAR(50) NULL,
    FOREIGN KEY (student_class_name) REFERENCES classes (class_name)
);

CREATE TABLE grades
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    value      INT NOT NULL,
    subject    VARCHAR(30),
    student_id INT NOT NULL,
    FOREIGN KEY (student_id) REFERENCES students (id)
);


