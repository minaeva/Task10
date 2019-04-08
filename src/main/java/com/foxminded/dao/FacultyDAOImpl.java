package com.foxminded.dao;

import com.foxminded.domain.Faculty;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAOImpl implements FacultyDAO {

    public Faculty insert(Faculty faculty) {
        String sql = "INSERT INTO faculties (name) VALUES (?);";

        try (Connection connection = DAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, faculty.getName());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Creating faculty failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    faculty.setId(generatedKeys.getLong("id"));
                } else {
                    throw new DAOException("Creating faculty failed, no generated key obtained.");
                }
            }
        }
        catch (SQLException e) {
            throw new DAOException("Cannot insert faculty " + faculty.getName(), e);
        }
        return faculty;
    }

    public Faculty findById(final long id){
       Faculty faculty = null;
       String sql = "SELECT id, name FROM faculties WHERE id = ?";

       try (Connection connection = DAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                   faculty = new Faculty(resultSet.getString("name"));
                   faculty.setId(resultSet.getInt("id"));
               }
            }
       }
       catch (SQLException e) {
            throw new DAOException("Cannot find faculty with id " + id, e);
       }
       return faculty;
    }

    public List<Faculty> findAll() {
        List<Faculty> result = null;
        String sql = "SELECT * FROM faculties;";

        try (Connection connection = DAOFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
                result = new ArrayList<>();
                while (resultSet.next()) {
                    Faculty faculty = new Faculty(resultSet.getString("name"));
                    faculty.setId(resultSet.getInt("id"));
                    result.add(faculty);
                }
            }
        catch (SQLException e) {
            throw new DAOException("Cannot find all faculties", e);
        }
        return result;
    }

    public Faculty update(final Faculty faculty) {
        if ((faculty.getId() == -1) || (faculty.getId() == 0)){
            throw new DAOException("Updating faculty failed, faculty should be created before update");
        }
        String sql = "UPDATE faculties SET name = (?) WHERE id = (?)";

        try (Connection connection = DAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {//, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, faculty.getName());
            statement.setLong(2, faculty.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Updating faculty failed, no rows affected.");
            }
        }
        catch (SQLException e) {
            throw new DAOException("Cannot insert faculty " + faculty.getName(), e);
        }
        return faculty;
    }

     public void delete(Faculty faculty) {
        String sql = "DELETE FROM faculties WHERE id = (?)";

        try (Connection connection = DAOFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, faculty.getId());
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new DAOException("Deleting faculty failed, no rows affected.");
            }
            else {
                faculty.setId(-1);
            }
        }
        catch (SQLException e) {
            throw new DAOException("Deleting faculty failed", e);
        }
    }
}
