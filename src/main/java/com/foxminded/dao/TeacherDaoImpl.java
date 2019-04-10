package com.foxminded.dao;

import com.foxminded.model.TeacherCard;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDaoImpl implements CrudDao<TeacherCard> {

    public TeacherCard create(TeacherCard teacher) {
        String sql = "INSERT INTO teachers (name) VALUES (?);";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, teacher.getName());
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                generatedKeys.next();
                teacher.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot create teacher " + teacher.getName(), e);
        }
        return teacher;
    }

    public TeacherCard findById(final long id){
        TeacherCard teacher = null;
        String sql = "SELECT id, name FROM teachers WHERE id = ?";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    teacher = new TeacherCard(resultSet.getString("name"));
                    teacher.setId(resultSet.getInt("id"));
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find teacher with id " + id, e);
        }
        return teacher;
    }

    public List<TeacherCard> findAll() {
        List<TeacherCard> result = null;
        String sql = "SELECT * FROM teachers;";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet == null){
                return null;
            }
            result = new ArrayList<>();
            while (resultSet.next()) {
                TeacherCard teacher = new TeacherCard(resultSet.getString("name"));
                teacher.setId(resultSet.getInt("id"));
                result.add(teacher);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot find all teachers ", e);
        }
        return result;
    }

    public TeacherCard update(final TeacherCard teacher) {
        if ((teacher.getId() == -1) || (teacher.getId() == 0)){
            throw new DaoException("Updating teacher failed, teacher should be created before update");
        }
        String sql = "UPDATE teachers SET name = (?) WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Cannot update teacher " + teacher.getName(), e);
        }
        return teacher;
    }

    public void delete(TeacherCard teacher) {
        String sql = "DELETE FROM teachers WHERE id = (?)";

        try (Connection connection = DaoConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, teacher.getId());
            statement.executeUpdate();
            teacher.setId(-1);
        } catch (SQLException e) {
            throw new DaoException("Cannot delete teacher ", e);
        }
    }
}
