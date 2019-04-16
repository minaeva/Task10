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
    subject_id INTEGER,
    auditorium_id INTEGER,
    start_date_time TIMESTAMP NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groups (id),
    FOREIGN KEY (teacher_id) REFERENCES teachers (id),
    FOREIGN KEY (subject_id) REFERENCES subjects (id),
    FOREIGN KEY (auditorium_id) REFERENCES auditoria (id),
    UNIQUE (group_id, start_dateTime)
);
