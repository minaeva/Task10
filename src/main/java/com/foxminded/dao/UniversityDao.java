package com.foxminded.dao;

import com.foxminded.domain.University;

import java.sql.*;

public class UniversityDao {

    public University insert(final String name) throws DAOException{
        University university = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO universities (id, name) VALUES (DEFAULT, (?));";

         try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            university = new University();
            university.setName(resultSet.getString("name"));
            university.setId(resultSet.getInt("id"));
        }
        catch (SQLException e) {
            throw new DAOException("Cannot find university " + name, e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when creating university " + name, e);
            }
        }
        return university;
    }


    public University getById(final long id) throws DAOException {
        University university = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id, name FROM universities WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();

            resultSet = statement.executeQuery();
            if (resultSet.next()){
                university = new University();
                university.setName(resultSet.getString("name"));
                university.setId(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            throw new DAOException("Cannot find university with id " + id, e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when finding university with id " + id, e);
                }
            }
        return university;
    }

    public University update(final University universityToUpdate) throws DAOException{
        University university = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE universities SET name = (?) WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(2, universityToUpdate.getId());
            statement.setString(1, universityToUpdate.getName());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
        }
        catch (SQLException e) {
            throw new DAOException("Cannot update university " + universityToUpdate.getName(), e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when renaming university " + universityToUpdate.getName(), e);
            }
        }
        return universityToUpdate;
    }

    public void delete(final University universityToDelete) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "DELETE FROM universities WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, universityToDelete.getId());
            statement.execute();
            universityToDelete.setId(-1);
        }
        catch (SQLException e) {
            throw new DAOException("Cannot delete university " + universityToDelete.getName(), e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when deleting university " + universityToDelete.getName(), e);
            }
        }
    }
}
