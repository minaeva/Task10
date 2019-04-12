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

INSERT INTO daySchedules (day, faculty_id) VALUES
    ('Mon', 1),
    ('Tue', 1),
    ('Wed', 1),
    ('Thu', 1),
    ('Fri', 1),
    ('Mon', 2),
    ('Tue', 2),
    ('Wed', 2),
    ('Thu', 2),
    ('Fri', 2);

INSERT INTO teachers (name, subject_id, faculty_id) VALUES
    ('Newton', 1, 1),
    ('Descartes', 3, 1),
    ('Einstein', 2, 1),
    ('Planck', 4, 1),
    ('Newton', 1, 2),
    ('Descartes', 3, 2),
    ('Einstein', 2, 2),
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

INSERT INTO lessons (group_id, teacher_id, auditorium_id, start_time, end_time, daySchedule_id) VALUES
    (1, 1, 1, '08:30:00', '10:00:00', 1),
    (1, 3, 1, '10:20:00', '11:50:00', 1),
    (1, 1, 2, '12:20:00', '13:50:00', 1),
    (1, 3, 2, '14:00:00', '15:30:00', 1),

    (1, 1, 1, '08:30:00', '10:00:00', 2),
    (1, 3, 1, '10:20:00', '11:50:00', 2),
    (1, 1, 2, '12:20:00', '13:50:00', 2),
    (1, 3, 2, '14:00:00', '15:30:00', 2),

    (1, 1, 1, '08:30:00', '10:00:00', 3),
    (1, 3, 1, '10:20:00', '11:50:00', 3),
    (1, 1, 2, '12:20:00', '13:50:00', 3),
    (1, 3, 2, '14:00:00', '15:30:00', 3),

    (1, 1, 1, '08:30:00', '10:00:00', 4),
    (1, 1, 1, '10:20:00', '11:50:00', 4),
    (1, 1, 1, '12:20:00', '13:50:00', 4),
    (1, 1, 1, '14:00:00', '15:30:00', 4),

    (1, 3, 2, '08:30:00', '10:00:00', 5),
    (1, 3, 2, '10:20:00', '11:50:00', 5),
    (1, 3, 2, '12:20:00', '13:50:00', 5),
    (1, 3, 2, '14:00:00', '15:30:00', 5),

    (2, 3, 3, '08:30:00', '10:00:00', 6),
    (2, 3, 4, '10:20:00', '11:50:00', 7),
    (2, 1, 3, '12:20:00', '13:50:00', 8),
    (2, 1, 4, '14:00:00', '15:30:00', 9);

