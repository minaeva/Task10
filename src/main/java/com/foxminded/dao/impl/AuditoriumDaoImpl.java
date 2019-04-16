package com.foxminded.dao.impl;

import com.foxminded.dao.AuditoriumDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Auditorium;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuditoriumDaoImpl implements AuditoriumDao {

    public Auditorium create(Auditorium auditorium) {
        String sql = "INSERT INTO auditoria (number) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, auditorium.getNumber());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                auditorium.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create auditorium " + auditorium.getNumber(), e);
        }
        return auditorium;
    }

    public Auditorium findById(final long id) {
        Auditorium auditorium = null;
        String sql = "SELECT id, number FROM auditoria WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    auditorium = new Auditorium(resultSet.getInt("number"));
                    auditorium.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find auditorium with id " + id, e);
        }
        return auditorium;
    }

    public List<Auditorium> findAll() {
        List<Auditorium> result = null;
        String sql = "SELECT * FROM auditoria;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Auditorium auditorium = new Auditorium(resultSet.getInt("number"));
                auditorium.setId(resultSet.getInt("id"));
                result.add(auditorium);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all auditoria ", e);
        }
        return result;
    }

    public List<Auditorium> findAuditoriaByFacultyId(long facultyId) {
        List<Auditorium> result = null;
        String sql = "SELECT id, name FROM auditoria WHERE faculty_id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Auditorium auditorium = new Auditorium(resultSet.getInt("number"));
                auditorium.setId(resultSet.getInt("id"));
                result.add(auditorium);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all auditoria of faculty with id " + facultyId, e);
        }
        return result;
    }

    public Auditorium update(final Auditorium auditorium) {
        if ((auditorium.getId() == -1) || (auditorium.getId() == 0)){
            throw new DaoException("Updating auditorium failed, auditorium should be created before update");
        }
        String sql = "UPDATE auditoria SET number = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, auditorium.getNumber());
            statement.setLong(2, auditorium.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update auditorium " + auditorium.getNumber(), e);
        }
        return auditorium;
    }

    public void delete(Auditorium auditorium) {
        String sql = "DELETE FROM auditoria WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, auditorium.getId());
            statement.executeUpdate();
            auditorium.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete auditorium ", e);
        }
    }

    public Auditorium addFacultyId(Auditorium auditorium, long facultyId) {
        String sql = "UPDATE auditoria SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, auditorium.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add faculty id " + facultyId + " to auditorium " + auditorium.getNumber(), e);
        }
        return auditorium;
    }

}

