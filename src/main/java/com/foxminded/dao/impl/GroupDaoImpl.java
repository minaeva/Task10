package com.foxminded.dao.impl;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {

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
                Group group = new Group(resultSet.getString("name"));
                group.setId(resultSet.getInt("id"));
                result.add(group);
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

    public StudentCard addStudent(long groupId, StudentCard student) {
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

    public void removeStudent(long groupId, StudentCard student) {
        String sql = "UPDATE students SET group_id = NULL WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, groupId);
            statement.setLong(2, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot remove student" + student.getName() + " from group with id " + groupId, e);
        }
    }

    public List<StudentCard> findAllGroupStudents(long groupId) {
        List<StudentCard> result = null;
        String sql = "SELECT id, name FROM students WHERE group_id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, groupId);
            ResultSet resultSet = statement.executeQuery();
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
                throw new DaoException("Cannot find all students of group with id " + groupId, e);
            }
            return result;
    }

    public long findByStudentId(final long studentId) {
        String sql = "SELECT group_id FROM students WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find group of student with id " + studentId, e);
        }
        return -1;
    }
}

