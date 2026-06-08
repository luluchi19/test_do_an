package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.util.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcStudentRepository implements StudentRepository {
    private static final String INSERT_SQL =
            "INSERT INTO students (id, name, age, class_name, gpa) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL =
            "UPDATE students SET name = ?, age = ?, class_name = ?, gpa = ? WHERE id = ?";
    private static final String DELETE_SQL =
            "DELETE FROM students WHERE id = ?";
    private static final String SELECT_BY_ID_SQL =
            "SELECT id, name, age, class_name, gpa FROM students WHERE id = ?";
    private static final String SELECT_ALL_SQL =
            "SELECT id, name, age, class_name, gpa FROM students";
    private static final String SEARCH_SQL =
            "SELECT id, name, age, class_name, gpa FROM students WHERE LOWER(name) LIKE LOWER(?)";

    @Override
    public boolean insert(Student student) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)) {
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getClassName());
            statement.setDouble(5, student.getGpa());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Student student) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getClassName());
            statement.setDouble(4, student.getGpa());
            statement.setString(5, student.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setString(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Optional<Student> findById(String id) {
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                students.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return students;
    }

    @Override
    public List<Student> searchByName(String keyword) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = DbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SEARCH_SQL)) {
            statement.setString(1, "%" + keyword + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    students.add(mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            return new ArrayList<>();
        }
        return students;
    }

    private Student mapRow(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getString("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("class_name"),
                resultSet.getDouble("gpa")
        );
    }
}
