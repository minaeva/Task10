CREATE DATABASE universitydb ENCODING 'UTF-8';

/*--------------*/
/* UNIVERSITIES */
CREATE TABLE IF NOT EXISTS universities (
                                   id SERIAL PRIMARY KEY,
                                   name VARCHAR(100) UNIQUE NOT NULL
);

INSERT INTO universities (id, name) VALUES 
(DEFAULT, 'KPI'),
(DEFAULT, 'DGU');


/*--------------*/
/* FACULTIES */
CREATE TABLE IF NOT EXISTS faculties (
                                   id SERIAL PRIMARY KEY,
                                   name VARCHAR(100) NOT NULL,
                                   university_id INTEGER NOT NULL,
                                   FOREIGN KEY (university_id) REFERENCES universities (id),
                                   UNIQUE (name, university_id)
);

INSERT INTO faculties (id, name, university_id) VALUES
(DEFAULT, 'Applied Math', 1),
(DEFAULT, 'Management', 1),
(DEFAULT, 'History', 2);


/*--------------*/
/* GROUPS */
CREATE TABLE IF NOT EXISTS groups (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) UNIQUE NOT NULL,
                                       faculty_id INTEGER NOT NULL,
                                       FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

INSERT INTO groups (id, name, faculty_id) VALUES
(DEFAULT, 'AM-01', 1),
(DEFAULT, 'AM-02', 1),
(DEFAULT, 'AM-03', 1),
(DEFAULT, 'MGM-01', 2),
(DEFAULT, 'MGM-02', 2),
(DEFAULT, 'HST-01', 3);


/*--------------*/
/* STUDENTS */
CREATE TABLE IF NOT EXISTS students (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(100) NOT NULL,
                                    group_id INTEGER NOT NULL,
                                    FOREIGN KEY (group_id) REFERENCES groups (id)
);

INSERT INTO students (id, name, group_id) VALUES
(DEFAULT, 'Ann', 1),
(DEFAULT, 'Alex', 1),
(DEFAULT, 'Marta', 1),
(DEFAULT, 'Max', 4),
(DEFAULT, 'Horton', 6);


/*--------------*/
/* JOURNALS */
CREATE TABLE IF NOT EXISTS journals (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(100) UNIQUE NOT NULL,
                                    group_id INTEGER NOT NULL,
                                    FOREIGN KEY (group_id) REFERENCES groups (id)
);

INSERT INTO journals (id, name, group_id) VALUES
(DEFAULT, 'J-AM-01', 1),
(DEFAULT, 'J-AM-02', 2),
(DEFAULT, 'J-AM-03', 3),
(DEFAULT, 'J-MGM-01', 4),
(DEFAULT, 'J-MGM-02', 5),
(DEFAULT, 'J-HST-01', 6);


/*--------------*/
/* SUBJECTS */
CREATE TABLE IF NOT EXISTS subjects (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      faculty_id INTEGER NOT NULL,
                                      FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

INSERT INTO subjects (id, name, faculty_id) VALUES
(DEFAULT, 'Math', 1),
(DEFAULT, 'Physics', 1),
(DEFAULT, 'Math', 2),
(DEFAULT, 'Physics', 2);


/*--------------*/
/* SECTIONS */
CREATE TABLE IF NOT EXISTS sections (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) UNIQUE NOT NULL,
                                      subject varchar(100) UNIQUE NOT NULL,
                                      journal_id INTEGER NOT NULL,
                                      FOREIGN KEY (journal_id) REFERENCES journals (id)
);

INSERT INTO sections (id, name, subject, journal_id) VALUES 
(DEFAULT, 'Math Section', 'Math', 1),
(DEFAULT, 'Physics Section', 'Physics', 1);



/*--------------*/
/* STUDENT MARKS */
CREATE TABLE IF NOT EXISTS student_marks (
                                      id SERIAL PRIMARY KEY,
                                      description VARCHAR(100) UNIQUE NOT NULL,
                                      student_id INTEGER NOT NULL,
                                      section_id INTEGER NOT NULL,
                                      FOREIGN KEY (student_id) REFERENCES students (id),
                                      FOREIGN KEY (section_id) REFERENCES sections (id)
);

INSERT INTO student_marks (id, description, student_id, section_id) VALUES
(DEFAULT, 'Ann Math Marks', 1, 1),
(DEFAULT, 'Ann Physics Marks', 1, 2),
(DEFAULT, 'Alex Math Marks', 2, 1),
(DEFAULT, 'Alex Physics Marks', 2, 2);



/*--------------*/
/* WEEKDAYS*/
CREATE TYPE weekDays AS ENUM ('Mon', 'Tue', 'Wed', 'Thu', 'Fri');



/*--------------*/
/* DAY SCHEDULE */
CREATE TABLE IF NOT EXISTS daySchedules (
                                       id SERIAL PRIMARY KEY,
                                       day weekDays,
                                       faculty_id INTEGER NOT NULL,
                                       FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

INSERT INTO daySchedules (id, day, faculty_id) VALUES
(DEFAULT, 'Mon', 1),
(DEFAULT, 'Tue', 1),
(DEFAULT, 'Wed', 1),
(DEFAULT, 'Thu', 1),
(DEFAULT, 'Fri', 1),
(DEFAULT, 'Mon', 2),
(DEFAULT, 'Tue', 2),
(DEFAULT, 'Wed', 2),
(DEFAULT, 'Thu', 2),
(DEFAULT, 'Fri', 2);



/*--------------*/
/* TEACHERS */
CREATE TABLE IF NOT EXISTS teachers (
                                      id SERIAL PRIMARY KEY,
                                      name VARCHAR(100) UNIQUE NOT NULL,
                                      subject_id INTEGER NOT NULL,
                                      FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

INSERT INTO teachers (id, name, subject_id) VALUES 
(DEFAULT, 'Newton', 1),
(DEFAULT, 'Descartes', 3),
(DEFAULT, 'Einstein', 2),
(DEFAULT, 'Planck', 4);



/*--------------*/
/* AUDITORIA */
CREATE TABLE IF NOT EXISTS auditoria (
                                       id SERIAL PRIMARY KEY,
                                       number INTEGER NOT NULL,
                                       faculty_id INTEGER NOT NULL,
                                       FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

INSERT INTO auditoria (id, number, faculty_id) VALUES 
(DEFAULT, 101, 1),
(DEFAULT, 102, 1),
(DEFAULT, 103, 1),
(DEFAULT, 104, 1),
(DEFAULT, 105, 1),
(DEFAULT, 201, 2),
(DEFAULT, 202, 2),
(DEFAULT, 203, 2),
(DEFAULT, 204, 2),
(DEFAULT, 205, 2);


/*--------------*/
/* LESSONS */
CREATE TABLE IF NOT EXISTS lessons (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(100) NOT NULL,
                                     group_id INTEGER NOT NULL,
                                     teacher_id INTEGER NOT NULL,
                                     auditorium_id INTEGER NOT NULL,
                                     start_time TIME NOT NULL,
                                     end_time TIME NOT NULL,
                                     daySchedule_id INTEGER not null,
                                     FOREIGN KEY (group_id) REFERENCES groups (id),
                                     FOREIGN KEY (teacher_id) REFERENCES teachers (id),
                                     FOREIGN KEY (auditorium_id) REFERENCES auditoria (id),
                                     FOREIGN KEY (daySchedule_id) REFERENCES daySchedules (id)
);
INSERT INTO products (product_no, name, price) VALUES
(1, 'Cheese', 9.99),
(2, 'Bread', 1.99),
(3, 'Milk', 2.99);


INSERT INTO lessons (id, name, group_id, teacher_id, auditorium_id, start_time, end_time, daySchedule_id) VALUES
(DEFAULT, 'Math_101', 1, 1, 1, '08:30:00', '10:00:00', 1),
(DEFAULT, 'Physics_101', 1, 3, 1, '10:20:00', '11:50:00', 1),
(DEFAULT, 'Math_102', 1, 1, 2, '12:20:00', '13:50:00', 1),
(DEFAULT, 'Physics_102', 1, 3, 2, '14:00:00', '15:30:00', 1),

(DEFAULT, 'Math_101', 1, 1, 1, '08:30:00', '10:00:00', 2),
(DEFAULT, 'Physics_101', 1, 3, 1, '10:20:00', '11:50:00', 2),
(DEFAULT, 'Math_102', 1, 1, 2, '12:20:00', '13:50:00', 2),
(DEFAULT, 'Physics_102', 1, 3, 2, '14:00:00', '15:30:00', 2),

(DEFAULT, 'Math_101', 1, 1, 1, '08:30:00', '10:00:00', 3),
(DEFAULT, 'Physics_101', 1, 3, 1, '10:20:00', '11:50:00', 3),
(DEFAULT, 'Math_102', 1, 1, 2, '12:20:00', '13:50:00', 3),
(DEFAULT, 'Physics_102', 1, 3, 2, '14:00:00', '15:30:00', 3),

(DEFAULT, 'Math_101', 1, 1, 1, '08:30:00', '10:00:00', 4),
(DEFAULT, 'Math_101', 1, 1, 1, '10:20:00', '11:50:00', 4),
(DEFAULT, 'Math_101', 1, 1, 1, '12:20:00', '13:50:00', 4),
(DEFAULT, 'Math_101', 1, 1, 1, '14:00:00', '15:30:00', 4),

(DEFAULT, 'Physics_102', 1, 3, 2, '08:30:00', '10:00:00', 5),
(DEFAULT, 'Physics_102', 1, 3, 2, '10:20:00', '11:50:00', 5),
(DEFAULT, 'Physics_102', 1, 3, 2, '12:20:00', '13:50:00', 5),
(DEFAULT, 'Physics_102', 1, 3, 2, '14:00:00', '15:30:00', 5),

(DEFAULT, 'Physics_103', 2, 3, 3, '08:30:00', '10:00:00', 6),
(DEFAULT, 'Physics_104', 2, 3, 4, '10:20:00', '11:50:00', 7),
(DEFAULT, 'Math_103', 2, 1, 3, '12:20:00', '13:50:00', 8),
(DEFAULT, 'Math_104', 2, 1, 4, '14:00:00', '15:30:00', 9);
