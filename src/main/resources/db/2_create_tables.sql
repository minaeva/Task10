/*--------------*/
/* FACULTIES */
CREATE TABLE IF NOT EXISTS faculties (
    id SERIAL UNIQUE PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

/*--------------*/
/* GROUPS */
CREATE TABLE IF NOT EXISTS groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    faculty_id INTEGER,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id),
    UNIQUE (name, faculty_id)
);

/*--------------*/
/* STUDENTS */
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    group_id INTEGER,
    FOREIGN KEY (group_id) REFERENCES groups (id)
);

/*--------------*/
/* SUBJECTS */
CREATE TABLE IF NOT EXISTS subjects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    faculty_id INTEGER,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id),
    UNIQUE (name, faculty_id)
);

/*--------------*/
/* DAY SCHEDULE */
CREATE TABLE IF NOT EXISTS daySchedules (
    id SERIAL PRIMARY KEY,
    day UNIQUE VARCHAR(20),
    faculty_id INTEGER,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

/*--------------*/
/* TEACHERS */
CREATE TABLE IF NOT EXISTS teachers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    subject_id INTEGER,
    faculty_id INTEGER,
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

/*--------------*/
/* AUDITORIA */
CREATE TABLE IF NOT EXISTS auditoria (
    id SERIAL PRIMARY KEY,
    number INTEGER NOT NULL,
    faculty_id INTEGER,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id),
    UNIQUE (number, faculty_id)
);

/*--------------*/
/* LESSONS */
CREATE TABLE IF NOT EXISTS lessons (
    id SERIAL PRIMARY KEY,
    group_id INTEGER,
    teacher_id INTEGER,
    auditorium_id INTEGER,
    start_time TIME NOT NULL,
    end_time TIME,
    daySchedule_id INTEGER,
    FOREIGN KEY (group_id) REFERENCES groups (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (id),
    FOREIGN KEY (auditorium_id) REFERENCES auditoria (id),
    FOREIGN KEY (daySchedule_id) REFERENCES daySchedules (id),
    UNIQUE (daySchedule_id, group_id, start_time)
);
