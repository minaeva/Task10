package com.foxminded.dao.impl;

import com.foxminded.dao.CrudDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements CrudDao<Group> {

    public Group create(Group group) {
        String sql = "INSERT INTO groups (name) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, group.getName());
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    group.setId(generatedKeys.getLong("id"));
                }
        } catch (SQLException e) {
            throw new DaoException("Cannot create group " + group.getName(), e);
        }
        return group;
    }

    public Group findById(final long id){
        Group group = null;
        String sql = "SELECT id, name FROM groups WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    group = new Group(resultSet.getString("name"));
                    group.setId(resultSet.getInt("id"));
                }
                }
        } catch (SQLException e) {
            throw new DaoException("Cannot find group with id " + id, e);
        }
        return group;
    }

    public List<Group> findAll() {
        List<Group> result = null;
        String sql = "SELECT * FROM groups;";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                Group Group = new Group(resultSet.getString("name"));
                Group.setId(resultSet.getInt("id"));
                result.add(Group);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all groups", e);
        }
        return result;
    }

    public Group update(final Group group) {
        if ((group.getId() == -1) || (group.getId() == 0)){
            throw new DaoException("Updating group failed, group should be created before update");
        }
        String sql = "UPDATE groups SET name = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, group.getName());
            statement.setLong(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update group " + group.getName(), e);
        }
        return group;
    }

    public void delete(Group group) {
        String sql = "DELETE FROM groups WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, group.getId());
            statement.executeUpdate();
            group.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete group ", e);
        }
    }

    public Group addFacultyId(Group group, long facultyId) {
        String sql = "UPDATE groups SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add faculty id " + facultyId + " to group " + group.getName(), e);
        }
        return group;
    }
}

