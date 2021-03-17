DROP TABLE IF EXISTS class, grade, lesson, student, teacher;
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

CREATE TABLE lessons
(
    id         INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    subject    VARCHAR(30),
    day        VARCHAR(50),
    class_name VARCHAR(50),
    FOREIGN KEY (class_name) REFERENCES classes (class_name),
    teacher_id INT NOT NULL,
    FOREIGN KEY (teacher_id) REFERENCES teachers (id)
);


