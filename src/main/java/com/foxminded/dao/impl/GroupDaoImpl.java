package com.foxminded.dao.impl;

import com.foxminded.dao.GroupDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class GroupDaoImpl implements GroupDao {

    private static Logger log = Logger.getLogger(GroupDaoImpl.class.getName());

    public Group create(Group group) {
        log.info("Creating group with name " + group.getName());
        String sql = "INSERT INTO groups (name) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                log.trace("Preparing statement");
                statement.setString(1, group.getName());
                statement.executeUpdate();
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    log.trace("Getting result set");
                    generatedKeys.next();
                    log.trace("Setting id for a group to return");
                    group.setId(generatedKeys.getLong("id"));
                }
        } catch (SQLException e) {
            log.error("Cannot create group " + group.getName(), e);
            throw new DaoException("Cannot create group " + group.getName(), e);
        }
        return group;
    }

    public Group findById(final long id){
        log.info("Finding group with id " + id);
        Group group = null;
        String sql = "SELECT id, name FROM groups WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Getting resultSet");
                if (resultSet.next()) {
                    log.trace("Creating a group to return");
                    group = new Group(resultSet.getString("name"));
                    group.setId(resultSet.getInt("id"));
                }
                }
        } catch (SQLException e) {
            log.error("Cannot find group with id " + id, e);
            throw new DaoException("Cannot find group with id " + id, e);
        }
        return group;
    }

    public List<Group> findAll() {
        log.info("Finding all groups");
        List<Group> result = null;
        String sql = "SELECT * FROM groups;";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery()) {
            log.trace("Getting result set");
            if (resultSet == null) {
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                log.trace("Creating a group from resultSet.next()");
                Group group = new Group(resultSet.getString("name"));
                group.setId(resultSet.getInt("id"));
                result.add(group);
            }
        } catch (SQLException e) {
            log.error("Cannot find all groups ", e);
            throw new DaoException("Cannot find all groups ", e);
        }
        return result;
    }

    public List<Group> findGroupsByFacultyId(long facultyId) {
        log.info("Finding group by faculty id " + facultyId);
        List<Group> result = null;
        String sql = "SELECT id, name FROM groups WHERE faculty_id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setLong(1, facultyId);
            log.trace("Getting result set");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
            log.trace("None found");
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                log.trace("Creating a group from resultSet.next()");
                Group group = new Group(resultSet.getString("name"));
                group.setId(resultSet.getInt("id"));
                result.add(group);
            }
        } catch (SQLException e) {
            log.error("Cannot find all groups of faculty with id " + facultyId, e);
            throw new DaoException("Cannot find all groups of faculty with id " + facultyId, e);
        }
        return result;
    }

    public Group update(final Group group) {
        log.info("Updating group with name " + group.getName());
        if ((group.getId() == -1) || (group.getId() == 0)){
            log.error("Updating group failed, group should be created before update");
            throw new DaoException("Updating group failed, group should be created before update");
        }
        String sql = "UPDATE groups SET name = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setString(1, group.getName());
            statement.setLong(2, group.getId());
            log.trace("Executing update");
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Cannot update group " + group.getName(), e);
            throw new DaoException("Cannot update group " + group.getName(), e);
        }
        return group;
    }

    public void delete(Group group) {
        log.info("Deleting group with name " + group.getName());
        String sql = "DELETE FROM groups WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setLong(1, group.getId());
            log.trace("Executing update");
            statement.executeUpdate();
            group.setId(-1);
        } catch (SQLException e) {
            log.error("Cannot delete group " + group.getName(), e);
            throw new DaoException("Cannot delete group " + group.getName(), e);
        }
    }

    public Group addStudent(long groupId, StudentCard student) {
        log.info("Adding student with name " + student.getName() + " to group with id " + groupId);
        String sql = "UPDATE students SET group_id = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setLong(1, groupId);
            statement.setLong(2, student.getId());
            log.trace("Executing update");
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Cannot add student " + student.getName() + " to group with id " + groupId, e);
            throw new DaoException("Cannot add student " + student.getName() + " to group with id " + groupId, e);
        }
        return findById(groupId);
    }

    public void removeStudent(long groupId, StudentCard student) {
        log.info("Removing student " + student.getName() + " from group with id " + groupId);
        String sql = "UPDATE students SET group_id = NULL WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setLong(1, groupId);
            statement.setLong(2, student.getId());
            log.trace("Executing update");
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("Cannot remove student " + student.getName() + " from group with id " + groupId, e);
            throw new DaoException("Cannot remove student" + student.getName() + " from group with id " + groupId, e);
        }
    }

    public Group findGroupByStudentId(final long studentId) {
        log.info("Finding group by student id " + studentId);
        String sql = "SELECT group_id FROM students WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            log.trace("Preparing statement");
            statement.setLong(1, studentId);
            try (ResultSet resultSet = statement.executeQuery()) {
                log.trace("Getting result set");
                if (resultSet.next()) {
                    log.trace("Group for student with id " + studentId + " found");
                    return findById(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            log.error("Cannot find group of a student with id " + studentId, e);
            throw new DaoException("Cannot find group of a student with id " + studentId, e);
        }
        return null;
    }
}

