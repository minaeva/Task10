package com.foxminded.dao.impl;

import com.foxminded.dao.CrudDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Auditorium;
import com.foxminded.model.Group;
import com.foxminded.model.Lesson;
import com.foxminded.model.TeacherCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements CrudDao<Lesson> {

    public Lesson create(Lesson lesson) {
        String sql = "INSERT INTO lessons (group_id, teacher_id, auditorium_id," +
                "start_time, end_time) VALUES (?, ?, ?, ?, ?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, lesson.getGroup().getId());
            statement.setLong(2, lesson.getTeacher().getId());
            statement.setLong(3, lesson.getAuditorium().getId());
            statement.setTime(4, Time.valueOf(lesson.getStartTime()));
            statement.setTime(5, Time.valueOf(lesson.getEndTime()));
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                lesson.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create lesson " + lesson.getStartTime(), e);
        }
        return lesson;
    }

    public Lesson findById(final long id){
        Lesson lesson = null;
        String sql = "SELECT id, group_id, teacher_id, auditorium_id, start_time, end_time FROM lessons WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    lesson = new Lesson(resultSet.getTime("start_time").toLocalTime());
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
            GroupDaoImpl groupDao = new GroupDaoImpl();
            Group group = groupDao.findById(resultSet.getLong("group_id"));
            lesson.setGroup(group);

            TeacherDaoImpl teacherDao = new TeacherDaoImpl();
            TeacherCard teacher = teacherDao.findById(resultSet.getLong("teacher_id"));
            lesson.setTeacher(teacher);

            AuditoriumDaoImpl auditoriumDao = new AuditoriumDaoImpl();
            Auditorium auditorium = auditoriumDao.findById(resultSet.getLong("auditorium_id"));
            lesson.setAuditorium(auditorium);

            lesson.setEndTime(resultSet.getTime("end_time").toLocalTime());
        } catch (SQLException e) {
            throw new DaoException("Cannot fill fields for lesson " + lesson.getStartTime(), e);
        }
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
                Lesson lesson = new Lesson(resultSet.getTime("start_time").toLocalTime());
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
        String sql = "UPDATE lessons SET group_id = (?), teacher_id = (?), auditorium_id = (?)," +
                "start_time = (?), end_time = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, lesson.getGroup().getId());
            statement.setLong(2, lesson.getTeacher().getId());
            statement.setLong(3, lesson.getAuditorium().getId());
            statement.setTime(4, Time.valueOf(lesson.getStartTime()));
            statement.setTime(5, Time.valueOf(lesson.getEndTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update lesson " + lesson.getStartTime(), e);
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

    public Lesson addDayScheduleId(Lesson lesson, long dayScheduleId) {
        String sql = "UPDATE lessons SET dayschedule_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, dayScheduleId);
            statement.setLong(2, lesson.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add day schedule id " + dayScheduleId +
                    " to lesson with start time " + lesson.getStartTime(), e);
        }
        return lesson;
    }
}
