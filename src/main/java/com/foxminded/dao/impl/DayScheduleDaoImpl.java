package com.foxminded.dao.impl;

import com.foxminded.dao.DayScheduleDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Lesson;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DayScheduleDaoImpl implements DayScheduleDao {

    public DaySchedule create(DaySchedule daySchedule) {
        String sql = "INSERT INTO dayschedules (day) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, daySchedule.getWorkDay().toString());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                daySchedule.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create day schedule " + daySchedule.getWorkDay(), e);
        }
        return daySchedule;
    }

    public DaySchedule findById(final long id) {
        DaySchedule daySchedule = null;
        String sql = "SELECT id, day FROM dayschedules WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    daySchedule = new DaySchedule(DayOfWeek.valueOf(resultSet.getString("day")));
                    daySchedule.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find day schedule with id " + id, e);
        }
        return daySchedule;
    }

    public List<DaySchedule> findAll() {
        List<DaySchedule> result = null;
        String sql = "SELECT * FROM dayschedules;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                DaySchedule daySchedule = new DaySchedule(DayOfWeek.valueOf(resultSet.getString("day")));
                daySchedule.setId(resultSet.getInt("id"));
                result.add(daySchedule);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all day schedules ", e);
        }
        return result;
    }

    public DaySchedule update(final DaySchedule daySchedule) {
        if ((daySchedule.getId() == -1) || (daySchedule.getId() == 0)) {
            throw new DaoException("Updating day schedule failed, day schedule should be created before update");
        }
        String sql = "UPDATE dayschedules SET day = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, daySchedule.getWorkDay().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update day schedule " + daySchedule.getWorkDay(), e);
        }
        return daySchedule;
    }

    public void delete(DaySchedule daySchedule) {
        String sql = "DELETE FROM dayschedules WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, daySchedule.getId());
            statement.executeUpdate();
            daySchedule.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete day schedule ", e);
        }
    }

    public List<DaySchedule> findAllByFacultyId(final long facultyId) {
        List<DaySchedule> result = null;
        String sql = "SELECT id, day FROM dayschedules WHERE faculty_id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            statement.setLong(1, facultyId);
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                DaySchedule daySchedule = new DaySchedule(DayOfWeek.valueOf(resultSet.getString("day")));
                daySchedule.setId(resultSet.getInt("id"));
                result.add(daySchedule);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find day schedules for faculty with id " + facultyId, e);
        }
        return result;
    }

    public DaySchedule addLesson(Lesson lesson, long dayScheduleId){
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
        return findById(dayScheduleId);
    }
}
