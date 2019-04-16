package com.foxminded.dao.impl;

import com.foxminded.dao.FacultyDao;
import com.foxminded.dao.DaoConnection;
import com.foxminded.dao.DaoException;
import com.foxminded.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultyDaoImpl implements FacultyDao {

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

    public Faculty addGroup(long facultyId, Group group){
        String sql = "UPDATE groups SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add faculty id " + facultyId + " to group " + group.getName(), e);
        }
        return findById(facultyId);
    }

    public void removeGroup(long facultyId, Group group){
        String sql = "UPDATE groups SET faculty_id = NULL WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot remove group " + group.getId() + " from faculty with id " + facultyId, e);
        }
    }

    public List<Group> findAllFacultyGroups(long facultyId) {
        List<Group> result = null;
        String sql = "SELECT id, name FROM groups WHERE faculty_id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            ResultSet resultSet = statement.executeQuery();
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
            throw new DaoException("Cannot find all groups of faculty with id " + facultyId, e);
        }
        return result;
    }

    public long findByGroupId(final long groupId) {
        String sql = "SELECT faculty_id FROM groups WHERE id = ?";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, groupId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find faculty of group with id " + groupId, e);
        }
        return -1;
    }

    public Faculty addTeacher(long facultyId, TeacherCard teacher){
        String sql = "UPDATE teachers SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, teacher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add teacher " + teacher.getName() + " to faculty with id " + facultyId, e);
        }
        return findById(facultyId);
    }

    public Faculty removeTeacher(long facultyId, TeacherCard teacher){
        String sql = "UPDATE teachers SET faculty_id = NULL WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, teacher.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot remove teacher " + teacher.getName() + " from faculty with id " + facultyId, e);
        }
        return findById(facultyId);
    }

    public Faculty addSubject(long facultyId, Subject subject) {
        String sql = "UPDATE subjects SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, subject.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add subject " + subject.getName() + " to faculty with id " + facultyId, e);
        }
        return findById(facultyId);
    }

    public Faculty removeSubject(long facultyId, Subject subject) {
        String sql = "UPDATE subjects SET faculty_id = NULL WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, subject.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot remove subject " + subject.getName() + " from faculty with id " + facultyId, e);
        }
        return findById(facultyId);
    }

    public Faculty addAuditorium(long facultyId, Auditorium auditorium) {
        String sql = "UPDATE subjects SET faculty_id = (?) WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, facultyId);
            statement.setLong(2, auditorium.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot add auditorium " + auditorium.getNumber() + " to faculty with id " + facultyId, e);
        }
        return findById(facultyId);
    }

    public Faculty removeAuditorium(long facultyId, Auditorium auditorium) {
        String sql = "UPDATE auditoria SET faculty_id = NULL WHERE id = (?)";
        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, auditorium.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot remove auditorium " + auditorium.getNumber() + " from faculty with id " + facultyId, e);
        }
        return findById(facultyId);
    }
}
