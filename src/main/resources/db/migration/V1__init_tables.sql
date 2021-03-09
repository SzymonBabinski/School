DROP TABLE IF EXISTS class, grade, lesson, student, teacher;
CREATE TABLE teacher
(
    id        INT NOT NULL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName  VARCHAR(50)
);

CREATE TABLE class
(
    className VARCHAR(50) PRIMARY KEY,
    FOREIGN KEY (className) REFERENCES teacher (id)
);

CREATE TABLE student
(
    id        INT NOT NULL PRIMARY KEY,
    firstName VARCHAR(50),
    lastName  VARCHAR(50),
    FOREIGN KEY (id) REFERENCES class (className)
);

CREATE TABLE grade
(
    id      INT NOT NULL PRIMARY KEY,
    value   INT NOT NULL,
    subject VARCHAR(30),
    FOREIGN KEY (id) REFERENCES student (id),
    FOREIGN KEY (id) REFERENCES teacher (id)
);

CREATE TABLE lesson
(
    id      INT NOT NULL PRIMARY KEY,
    subject VARCHAR(30),
    day     VARCHAR(50),
    FOREIGN KEY (id) REFERENCES class (className),
    FOREIGN KEY (id) REFERENCES teacher (id)
);


