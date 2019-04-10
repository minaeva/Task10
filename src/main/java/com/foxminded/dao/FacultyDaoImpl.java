package com.foxminded.dao;

import com.foxminded.model.Faculty;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl implements CrudDao<Faculty> {

    public Faculty create(Faculty faculty) {
        String sql = "INSERT INTO faculties (name) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, faculty.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                faculty.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create faculty " + faculty.getName(), e);
        }
        return faculty;
    }

    public Faculty findById(final long id){
       Faculty faculty = null;
       String sql = "SELECT id, name FROM faculties WHERE id = ?";

       try (Connection connection = DaoConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                 if (resultSet.next()) {
                    faculty = new Faculty(resultSet.getString("name"));
                    faculty.setId(resultSet.getInt("id"));
                }
            }
       } catch (SQLException e) {
            throw new DaoException("Cannot find faculty with id " + id, e);
       }
       return faculty;
    }

    public List<Faculty> findAll() {
        List<Faculty> result = null;
        String sql = "SELECT * FROM faculties;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                if (resultSet == null){
                    return null;
                }
                result = new ArrayList<>();
                while (resultSet.next()) {
                    Faculty faculty = new Faculty(resultSet.getString("name"));
                    faculty.setId(resultSet.getInt("id"));
                    result.add(faculty);
                }
            } catch (SQLException e) {
                throw new DaoException("Cannot find all faculties ", e);
        }
        return result;
    }

    public Faculty update(final Faculty faculty) {
        if ((faculty.getId() == -1) || (faculty.getId() == 0)){
            throw new DaoException("Updating faculty failed, faculty should be created before update");
        }
        String sql = "UPDATE faculties SET name = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, faculty.getName());
            statement.setLong(2, faculty.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update faculty " + faculty.getName(), e);
        }
        return faculty;
    }

     public void delete(Faculty faculty) {
        String sql = "DELETE FROM faculties WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, faculty.getId());
            statement.executeUpdate();
            faculty.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete faculty ", e);
        }
    }
}
