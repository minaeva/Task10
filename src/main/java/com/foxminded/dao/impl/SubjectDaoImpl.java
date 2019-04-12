package com.foxminded.dao.impl;

import com.foxminded.dao.CrudDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Subject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements CrudDao<Subject> {

    public Subject create(Subject subject) {
        String sql = "INSERT INTO subjects (name) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, subject.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                subject.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create subject " + subject.getName(), e);
        }
        return subject;
    }

    public Subject findById(final long id){
        Subject subject = null;
        String sql = "SELECT id, name FROM subjects WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    subject = new Subject(resultSet.getString("name"));
                    subject.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find subject with id " + id, e);
        }
        return subject;
    }

    public List<Subject> findAll() {
        List<Subject> result = null;
        String sql = "SELECT * FROM subjects;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null){
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Subject subject = new Subject(resultSet.getString("name"));
                subject.setId(resultSet.getInt("id"));
                result.add(subject);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all subjects ", e);
        }
        return result;
    }

    public Subject update(final Subject subject) {
        if ((subject.getId() == -1) || (subject.getId() == 0)){
            throw new DaoException("Updating subject failed, subject should be created before update");
        }
        String sql = "UPDATE subjects SET name = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, subject.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update subject " + subject.getName(), e);
        }
        return subject;
    }

    public void delete(Subject subject) {
        String sql = "DELETE FROM subjects WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, subject.getId());
            statement.executeUpdate();
            subject.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete subject ", e);
        }
    }

    public Subject addFacultyId(Subject subject, long facultyId) {
        String sql = "UPDATE subjects SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, subject.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add faculty id " + facultyId + " to subject " + subject.getName(), e);
        }
        return subject;
    }
}
