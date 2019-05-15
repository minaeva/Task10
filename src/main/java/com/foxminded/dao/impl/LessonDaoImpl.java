package com.foxminded.dao.impl;

import com.foxminded.dao.*;
import com.foxminded.model.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {

    public Lesson create(Lesson lesson) {
        String sql = "INSERT INTO lessons (group_id, teacher_id, subject_id, auditorium_id," +
                "start_date_time) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, lesson.getGroup().getId());
            statement.setLong(2, lesson.getTeacher().getId());
            statement.setLong(3, lesson.getSubject().getId());
            statement.setLong(4, lesson.getAuditorium().getId());
            statement.setTimestamp(5, Timestamp.valueOf(lesson.getStartDateTime()));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                lesson.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create lesson " + lesson.getStartDateTime(), e);
        }
        return lesson;
    }

    public Lesson findById(final long id){
        Lesson lesson = null;
        String sql = "SELECT id, group_id, teacher_id, subject_id, auditorium_id, start_date_time FROM lessons WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lesson = new Lesson(resultSet.getTimestamp("start_date_time").toLocalDateTime());
                    lesson.setId(resultSet.getInt("id"));
                    fillReferenceFields(resultSet, lesson);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find lesson with id " + id, e);
        }
        return lesson;
    }

    private void fillReferenceFields(ResultSet resultSet, Lesson lesson){
        try {
            GroupDao groupDao = new GroupDaoImpl();
            Group group = groupDao.findById(resultSet.getLong("group_id"));
            lesson.setGroup(group);

            TeacherDao teacherDao = new TeacherDaoImpl();
            TeacherCard teacher = teacherDao.findById(resultSet.getLong("teacher_id"));
            lesson.setTeacher(teacher);

            AuditoriumDao auditoriumDao = new AuditoriumDaoImpl();
            Auditorium auditorium = auditoriumDao.findById(resultSet.getLong("auditorium_id"));
            lesson.setAuditorium(auditorium);

            SubjectDao subjectDao = new SubjectDaoImpl();
            Subject subject = subjectDao.findById(resultSet.getLong("subject_id"));
            lesson.setSubject(subject);
        } catch (SQLException e) {
            throw new DaoException("Cannot fill fields for lesson " + lesson.getStartDateTime(), e);
        }
    }

    public List<Lesson> findLessonsByGroupId(long groupId) {
        List<Lesson> result = null;
        String sql = "SELECT id, teacher_id, subject_id, auditorium_id, start_date_time FROM lessons WHERE group_id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Lesson lesson = new Lesson(resultSet.getTimestamp("start_date_time").toLocalDateTime());
                lesson.setId(resultSet.getInt("id"));

                Group group = new Group();
                group.setId(groupId);
                lesson.setGroup(group);

                TeacherCard teacher = new TeacherCard();
                teacher.setId(resultSet.getInt("teacher_id"));
                lesson.setTeacher(teacher);

                addAuditoriumSubjectToLesson(resultSet.getInt("auditorium_id"), resultSet.getInt("subject_id"), lesson);

                result.add(lesson);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all lessons of group with id " + groupId, e);
        }
        return result;
    }

    public List<Lesson> findLessonsByGroupIdInPeriod(long groupId, LocalDate from, LocalDate to) {
        List<Lesson> result = null;
        String sql = "SELECT id, teacher_id, subject_id, auditorium_id, start_date_time FROM lessons " +
                "WHERE group_id = (?) AND start_date_time >= (?) AND start_date_time < (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, groupId);
            statement.setDate(2, Date.valueOf(from));
            statement.setDate(3, Date.valueOf(to.plusDays(1)));
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                LocalDate date = resultSet.getTimestamp("start_date_time").toLocalDateTime().toLocalDate();
                    Lesson lesson = new Lesson(resultSet.getTimestamp("start_date_time").toLocalDateTime());
                    lesson.setId(resultSet.getInt("id"));

                    Group group = new Group();
                    group.setId(groupId);
                    lesson.setGroup(group);

                    TeacherCard teacher = new TeacherCard();
                    teacher.setId(resultSet.getInt("teacher_id"));
                    lesson.setTeacher(teacher);

                    addAuditoriumSubjectToLesson(resultSet.getInt("auditorium_id"), resultSet.getInt("subject_id"), lesson);

                    result.add(lesson);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all lessons of group with id " + groupId + " in period " + from + "-" +to, e);
        }
        return result;
    }

    private void addAuditoriumSubjectToLesson(int auditoriumId, int subjectId, Lesson lesson) {
        Auditorium auditorium = new Auditorium();
        auditorium.setId(auditoriumId);
        lesson.setAuditorium(auditorium);

        Subject subject = new Subject();
        subject.setId(subjectId);
        lesson.setSubject(subject);
    }

    public List<Lesson> findLessonsByTeacherId(long teacherId) {
        List<Lesson> result = null;
        String sql = "SELECT id, group_id, subject_id, auditorium_id, start_date_time FROM lessons WHERE teacher_id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, teacherId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Lesson lesson = new Lesson(resultSet.getTimestamp("start_date_time").toLocalDateTime());
                lesson.setId(resultSet.getInt("id"));

                Group group = new Group();
                group.setId(resultSet.getInt("group_id"));
                lesson.setGroup(group);

                TeacherCard teacher = new TeacherCard();
                teacher.setId(teacherId);
                lesson.setTeacher(teacher);

                addAuditoriumSubjectToLesson(resultSet.getInt("auditorium_id"), resultSet.getInt("subject_id"), lesson);

                result.add(lesson);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all lessons of teacher with id " + teacherId, e);
        }
        return result;
    }

    public List<Lesson> findAll() {
        List<Lesson> result = null;
        String sql = "SELECT * FROM lessons;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null){
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Lesson lesson = new Lesson(resultSet.getTimestamp("start_date_time").toLocalDateTime());
                lesson.setId(resultSet.getInt("id"));
                fillReferenceFields(resultSet, lesson);
                result.add(lesson);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all lessons ", e);
        }
        return result;
    }

    public Lesson update(final Lesson lesson) {
        if ((lesson.getId() == -1) || (lesson.getId() == 0)){
            throw new DaoException("Updating lesson failed, lesson should be created before update");
        }
        String sql = "UPDATE lessons SET group_id = (?), teacher_id = (?), subject_id = (?), auditorium_id = (?)," +
                "start_date_time = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, lesson.getGroup().getId());
            statement.setLong(2, lesson.getSubject().getId());
            statement.setLong(3, lesson.getTeacher().getId());
            statement.setLong(4, lesson.getAuditorium().getId());
            statement.setTimestamp(5, Timestamp.valueOf(lesson.getStartDateTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update lesson " + lesson.getStartDateTime(), e);
        }
        return lesson;
    }

    public void delete(Lesson lesson) {
        String sql = "DELETE FROM lessons WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, lesson.getId());
            statement.executeUpdate();
            lesson.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete lesson ", e);
        }
    }

    public Lesson addTeacher(Lesson lesson, TeacherCard teacher) {
        String sql = "UPDATE lessons SET teacher_id = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, teacher.getId());
            statement.setLong(2, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add teacher " + teacher.getName() + " to lesson " + lesson.getStartDateTime(), e);
        }
        return lesson;
    }

    public Lesson addSubject(Lesson lesson, Subject subject) {
        String sql = "UPDATE lessons SET subject_id = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, subject.getId());
            statement.setLong(2, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add subject " + subject.getName() + " to lesson " + lesson.getStartDateTime(), e);
        }
        return lesson;
    }

    public Lesson addAuditorium(Lesson lesson, Auditorium auditorium) {
        String sql = "UPDATE lessons SET auditorium_id = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, auditorium.getId());
            statement.setLong(2, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add auditorium " + auditorium.getNumber() + " to lesson " + lesson.getStartDateTime(), e);
        }
        return lesson;
    }
}
