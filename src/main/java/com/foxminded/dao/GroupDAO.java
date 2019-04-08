package com.foxminded.dao;

import com.foxminded.domain.Group;

import java.sql.*;

public class GroupDAO {

 /*   public Group insert(final String name) throws DAOException{
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "INSERT INTO groups (id, name) VALUES (DEFAULT, (?));";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            resultSet.next();

            group = new Group(resultSet.getString("name"));
            group.setId(resultSet.getInt("id"));
        }
        catch (SQLException e) {
            throw new DAOException("Cannot find Group " + name, e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when creating Group " + name, e);
            }
        }
        return group;
    }


    public Group getById(final long id) throws DAOException {
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id, name FROM groups WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute();

            resultSet = statement.executeQuery();
            if (resultSet.next()){
                group = new Group(resultSet.getString("name"));
                group.setId(resultSet.getInt("id"));
            }
        }
        catch (SQLException e) {
            throw new DAOException("Cannot find Group with id " + id, e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when finding Group with id " + id, e);
            }
        }
        return group;
    }

    public Group update(final Group groupToUpdate) throws DAOException{
        Group group = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "UPDATE groups SET name = (?) WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(2, groupToUpdate.getId());
            statement.setString(1, groupToUpdate.getName());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
        }
        catch (SQLException e) {
            throw new DAOException("Cannot update Group " + groupToUpdate.getName(), e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when updating Group " + groupToUpdate.getName(), e);
            }
        }
        return groupToUpdate;
    }

    public void delete(final Group groupToDelete) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "DELETE FROM groups WHERE id = (?)";

        try {
            connection = DAOFactory.getConnection();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, groupToDelete.getId());
            statement.execute();
        }
        catch (SQLException e) {
            throw new DAOException("Cannot delete Group " + groupToDelete.getName(), e);
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
                throw new DAOException("Cannot close connection/statement/resultSet when deleting Group " + groupToDelete.getName(), e);
            }
        }
    }

  */
}
