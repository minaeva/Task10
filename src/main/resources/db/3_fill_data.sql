INSERT INTO faculties (name) VALUES
    ('Applied Math'),
    ('Management'),
    ('History');

INSERT INTO groups (name, faculty_id) VALUES
    ('AM-01', 1),
    ('AM-02', 1),
    ('AM-03', 1),
    ('MGM-01', 2),
    ('MGM-02', 2),
    ('HST-01', 3);

INSERT INTO students (name, group_id) VALUES
    ('Ann', 1),
    ('Alex', 1),
    ('Marta', 1),
    ('Max', 4),
    ('Horton', 6);

INSERT INTO subjects (name, faculty_id) VALUES
    ('Math', 1),
    ('Physics', 1),
    ('Math', 2),
    ('Physics', 2);

INSERT INTO teachers (name, subject_id, faculty_id) VALUES
    ('Newton', 1, 1),
    ('Descartes', 1, 1),
    ('Einstein', 2, 1),
    ('Planck', 2, 1),
    ('Newton', 3, 2),
    ('Descartes', 3, 2),
    ('Einstein', 4, 2),
    ('Planck', 4, 2);

INSERT INTO auditoria (number, faculty_id) VALUES
    (101, 1),
    (102, 1),
    (103, 1),
    (104, 1),
    (105, 1),
    (201, 2),
    (202, 2),
    (203, 2),
    (204, 2),
    (205, 2);

INSERT INTO lessons (group_id, teacher_id, subject_id, auditorium_id, start_date_time) VALUES
    (1, 1, 1, 1, '2019-04-15 08:30:00+2'),
    (1, 3, 2, 2, '2019-04-15 10:20:00+2'),
    (1, 2, 1, 3, '2019-04-15 12:20:00+2'),
    (1, 3, 2, 4, '2019-04-15 14:00:00+2'),

    (1, 1, 1, 5, '2019-04-16 08:30:00+2'),
    (1, 3, 2, 5, '2019-04-16 10:20:00+2'),
    (1, 2, 1, 5, '2019-04-16 12:20:00+2'),
    (1, 3, 2, 5, '2019-04-16 14:00:00+2'),

    (1, 1, 1, 4, '2019-04-17 08:30:00+2'),
    (1, 1, 1, 4, '2019-04-17 10:20:00+2'),
    (1, 1, 1, 4, '2019-04-17 12:20:00+2'),
    (1, 1, 1, 4, '2019-04-17 14:00:00+2'),

    (1, 3, 1, 3, '2019-04-18 08:30:00+2'),
    (1, 3, 1, 3, '2019-04-18 10:20:00+2'),
    (1, 3, 1, 3, '2019-04-18 12:20:00+2'),
    (1, 3, 1, 3, '2019-04-18 14:00:00+2'),

    (1, 4, 2, 4, '2019-04-19 08:30:00+2'),
    (1, 3, 2, 3, '2019-04-19 10:20:00+2'),
    (1, 2, 1, 2, '2019-04-19 12:20:00+2'),
    (1, 1, 1, 1, '2019-04-19 14:00:00+2'),

    (2, 5, 3, 6, '2019-04-15 08:30:00+2'),
    (2, 6, 3, 7, '2019-04-16 10:20:00+2'),
    (2, 5, 3, 8, '2019-04-17 12:20:00+2'),
    (2, 6, 3, 9, '2019-04-18 14:00:00+2'),
    (2, 5, 3, 9, '2019-04-19 15:40:00+2');
