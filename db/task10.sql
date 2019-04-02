CREATE DATABASE universityDB ENCODING 'UTF-8';
SET search_path = universityDB;

/*--------------*/
/* UNIVERSITIES */
CREATE TABLE IF NOT EXISTS universities (
                                   id   SERIAL PRIMARY KEY,
                                   name VARCHAR(100) NOT NULL
);

INSERT INTO universities (id, name) VALUES (DEFAULT, 'KPI');
INSERT INTO universities (id, name) VALUES (DEFAULT, 'DGU');


/*--------------*/
/* FACULTIES */
CREATE TABLE IF NOT EXISTS faculties (
                                   id      SERIAL PRIMARY KEY,
                                   name    VARCHAR(100) UNIQUE NOT NULL,
                                   university_id     INTEGER     NOT NULL,
                                   FOREIGN KEY (university_id) REFERENCES universities (id)
);

INSERT INTO faculties (id, name, university_id) VALUES (DEFAULT, 'Applied Math', 1);
INSERT INTO faculties (id, name, university_id) VALUES (DEFAULT, 'Management', 1);
INSERT INTO faculties (id, name, university_id) VALUES (DEFAULT, 'History', 2);


/*--------------*/
/* GROUPS */
CREATE TABLE IF NOT EXISTS groups (
                                       id      SERIAL PRIMARY KEY,
                                       name    VARCHAR(100) UNIQUE NOT NULL,
                                       faculty_id     INTEGER     NOT NULL,
                                       FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

INSERT INTO groups (id, name, faculty_id) VALUES (DEFAULT, 'AM-01', 1); /*id = 1*/
INSERT INTO groups (id, name, faculty_id) VALUES (DEFAULT, 'AM-02', 1);
INSERT INTO groups (id, name, faculty_id) VALUES (DEFAULT, 'AM-03', 1);
INSERT INTO groups (id, name, faculty_id) VALUES (DEFAULT, 'MGM-01', 2);
INSERT INTO groups (id, name, faculty_id) VALUES (DEFAULT, 'MGM-02', 2);
INSERT INTO groups (id, name, faculty_id) VALUES (DEFAULT, 'HST-01', 3);


/*--------------*/
/* STUDENTS */
CREATE TABLE IF NOT EXISTS students (
                                    id      SERIAL PRIMARY KEY,
                                    name    VARCHAR(100) UNIQUE NOT NULL,
                                    group_id     INTEGER     NOT NULL,
                                    FOREIGN KEY (group_id) REFERENCES groups (id)
);

INSERT INTO students (id, name, group_id) VALUES (DEFAULT, 'Ann', 1);
INSERT INTO students (id, name, group_id) VALUES (DEFAULT, 'Alex', 1);
INSERT INTO students (id, name, group_id) VALUES (DEFAULT, 'Marta', 1);
INSERT INTO students (id, name, group_id) VALUES (DEFAULT, 'Max', 4);
INSERT INTO students (id, name, group_id) VALUES (DEFAULT, 'Horton', 6);


/*--------------*/
/* JOURNALS */
CREATE TABLE IF NOT EXISTS journals (
                                    id      SERIAL PRIMARY KEY,
                                    name    VARCHAR(100) UNIQUE NOT NULL,
                                    group_id     INTEGER     NOT NULL,
                                    FOREIGN KEY (group_id) REFERENCES groups (id)
);

INSERT INTO journals (id, name, group_id) VALUES (DEFAULT, 'J-AM-01', 1);
INSERT INTO journals (id, name, group_id) VALUES (DEFAULT, 'J-AM-02', 2);
INSERT INTO journals (id, name, group_id) VALUES (DEFAULT, 'J-AM-03', 3);
INSERT INTO journals (id, name, group_id) VALUES (DEFAULT, 'J-MGM-01', 4);
INSERT INTO journals (id, name, group_id) VALUES (DEFAULT, 'J-MGM-02', 5);
INSERT INTO journals (id, name, group_id) VALUES (DEFAULT, 'J-HST-01', 6);


/*--------------*/
/* SUBJECTS */
CREATE TABLE IF NOT EXISTS subjects (
                                      id      SERIAL PRIMARY KEY,
                                      name    VARCHAR(100) UNIQUE NOT NULL,
                                      faculty_id     INTEGER     NOT NULL,
                                      FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

INSERT INTO subjects (id, name, faculty_id) VALUES (DEFAULT, 'Math', 1);
INSERT INTO subjects (id, name, faculty_id) VALUES (DEFAULT, 'Physics', 1);


/*--------------*/
/* SECTIONS */
CREATE TABLE IF NOT EXISTS sections (
                                      id      SERIAL PRIMARY KEY,
                                      name    VARCHAR(100) UNIQUE NOT NULL,
                                      subject varchar(100) UNIQUE NOT NULL,
                                      journal_id     INTEGER     NOT NULL,
                                      FOREIGN KEY (journal_id) REFERENCES journals (id)
);

INSERT INTO sections (id, name, subject, journal_id) VALUES (DEFAULT, 'Math Section', 'Math', 1);
INSERT INTO sections (id, name, subject, journal_id) VALUES (DEFAULT, 'Physics Section', 'Physics', 1);



/*--------------*/
/* STUDENT MARKS */
CREATE TABLE IF NOT EXISTS student_marks (
                                      id      SERIAL PRIMARY KEY,
                                      description varchar(100) UNIQUE NOT NULL,
                                      student_id     INTEGER     NOT NULL,
                                      section_id     INTEGER     NOT NULL,
                                      FOREIGN KEY (student_id) REFERENCES students (id),
                                      FOREIGN KEY (section_id) REFERENCES sections (id)
);

INSERT INTO student_marks (id, description, student_id, section_id) VALUES (DEFAULT, 'Ann Math Marks', 1, 1);
INSERT INTO student_marks (id, description, student_id, section_id) VALUES (DEFAULT, 'Ann Physics Marks', 1, 2);
INSERT INTO student_marks (id, description, student_id, section_id) VALUES (DEFAULT, 'Alex Math Marks', 2, 1);
INSERT INTO student_marks (id, description, student_id, section_id) VALUES (DEFAULT, 'Alex Physics Marks', 2, 2);

