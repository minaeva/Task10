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
    faculty_id INTEGER NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

/*--------------*/
/* STUDENTS */
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    group_id INTEGER NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups (id)
);

/*--------------*/
/* SUBJECTS */
CREATE TABLE IF NOT EXISTS subjects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    faculty_id INTEGER NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

/*--------------*/
/* DAY SCHEDULE */
CREATE TABLE IF NOT EXISTS daySchedules (
    id SERIAL PRIMARY KEY,
    day VARCHAR(20),
    faculty_id INTEGER NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

/*--------------*/
/* TEACHERS */
CREATE TABLE IF NOT EXISTS teachers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    subject_id INTEGER NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES subjects (id)
);

/*--------------*/
/* AUDITORIA */
CREATE TABLE IF NOT EXISTS auditoria (
    id SERIAL PRIMARY KEY,
    number INTEGER NOT NULL,
    faculty_id INTEGER NOT NULL,
    FOREIGN KEY (faculty_id) REFERENCES faculties (id)
);

/*--------------*/
/* LESSONS */
CREATE TABLE IF NOT EXISTS lessons (
    id SERIAL PRIMARY KEY,
    group_id INTEGER NOT NULL,
    teacher_id INTEGER NOT NULL,
    auditorium_id INTEGER NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    daySchedule_id INTEGER not null,
    FOREIGN KEY (group_id) REFERENCES groups (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (id),
    FOREIGN KEY (auditorium_id) REFERENCES auditoria (id),
    FOREIGN KEY (daySchedule_id) REFERENCES daySchedules (id),
    UNIQUE (daySchedule_id, group_id, start_time)
);

