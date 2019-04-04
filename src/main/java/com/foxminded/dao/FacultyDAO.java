package com.foxminded.dao;

import com.foxminded.domain.Faculty;
import java.sql.*;

public class FacultyDAO {

    public Faculty insert(final String name) throws DAOException{
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO faculties (id, name) VALUES (DEFAULT, (?));";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            faculty = new Faculty(resultSet.getString("name"));
            faculty.setId(resultSet.getInt("id"));
        }
        catch (SQLException e) {
            throw new DAOException("Cannot find faculty " + name, e);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                throw new DAOException("Cannot close connection/statement/resultSet when creating faculty " + name, e);
            }
        }
        return faculty;
    }


    public Faculty getById(final long id) throws DAOException {
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id, name FROM faculties WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();

            resultSet = statement.executeQuery();
            if (resultSet.next()){
                faculty = new Faculty(resultSet.getString("name"));
                faculty.setId(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            throw new DAOException("Cannot find faculty with id " + id, e);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                throw new DAOException("Cannot close connection/statement/resultSet when finding faculty with id " + id, e);
            }
        }
        return faculty;
    }

    public Faculty update(final Faculty facultyToUpdate) throws DAOException{
        Faculty faculty = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE faculties SET name = (?) WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(2, facultyToUpdate.getId());
            statement.setString(1, facultyToUpdate.getName());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
        }
        catch (SQLException e) {
            throw new DAOException("Cannot update faculty " + facultyToUpdate.getName(), e);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                throw new DAOException("Cannot close connection/statement/resultSet when updating faculty " + facultyToUpdate.getName(), e);
            }
        }
        return facultyToUpdate;
    }

    public void delete(final Faculty facultyToDelete) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "DELETE FROM faculties WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, facultyToDelete.getId());
            statement.execute();
        }
        catch (SQLException e) {
            throw new DAOException("Cannot delete faculty " + facultyToDelete.getName(), e);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            try {
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
                if (connection != null){
                    connection.close();
                }
            }
            catch (SQLException e){
                throw new DAOException("Cannot close connection/statement/resultSet when deleting faculty " + facultyToDelete.getName(), e);
            }
        }
    }
}
