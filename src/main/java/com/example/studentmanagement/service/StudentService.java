package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.repository.InMemoryStudentRepository;
import com.example.studentmanagement.repository.JdbcStudentRepository;
import com.example.studentmanagement.repository.StudentRepository;
import com.example.studentmanagement.util.DbConnection;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.sql.Connection;
import java.sql.SQLException;

public class StudentService {
    private final StudentRepository repository;

    public StudentService() {
        this.repository = createRepository();
    }

    public boolean addStudent(Student student) {
        return repository.insert(student);
    }

    public boolean updateStudent(String id, Student updatedStudent) {
        updatedStudent.setId(id);
        return repository.update(updatedStudent);
    }

    public boolean deleteStudent(String id) {
        return repository.deleteById(id);
    }

    public Optional<Student> findById(String id) {
        return repository.findById(id);
    }

    public List<Student> searchByName(String keyword) {
        return repository.searchByName(keyword);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public List<Student> sortByGpaDesc() {
        return repository.findAll().stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .toList();
    }

    private StudentRepository createRepository() {
        try (Connection ignored = DbConnection.getConnection()) {
            return new JdbcStudentRepository();
        } catch (SQLException e) {
            return new InMemoryStudentRepository();
        }
    }
}
