package com.foxminded.dao.impl;

import com.foxminded.dao.StudentDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.StudentCard;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    public StudentCard create(StudentCard student) {
        String sql = "INSERT INTO students (name) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, student.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                student.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create student " + student.getName(), e);
        }
        return student;
    }

    public StudentCard findById(final long id){
        StudentCard student = null;
        String sql = "SELECT id, name FROM students WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    student = new StudentCard(resultSet.getString("name"));
                    student.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find student with id " + id, e);
        }
        return student;
    }

    public List<StudentCard> findAll() {
        List<StudentCard> result = null;
        String sql = "SELECT * FROM students;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                StudentCard student = new StudentCard(resultSet.getString("name"));
                student.setId(resultSet.getInt("id"));
                result.add(student);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all students ", e);
        }
        return result;
    }

    public StudentCard update(final StudentCard student) {
        if ((student.getId() == -1) || (student.getId() == 0)){
            throw new DaoException("Updating student failed, student should be created before update");
        }
        String sql = "UPDATE students SET name = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setLong(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update student " + student.getName(), e);
        }
        return student;
    }

    public void delete(StudentCard student) {
        String sql = "DELETE FROM students WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, student.getId());
            statement.executeUpdate();
            student.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete student ", e);
        }
    }

    public StudentCard addGroupId(StudentCard student, long groupId) {
        String sql = "UPDATE students SET group_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, groupId);
            statement.setLong(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add group id " + groupId + " to student " + student.getName(), e);
        }
        return student;
    }
}

