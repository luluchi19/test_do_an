package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    boolean insert(Student student);

    boolean update(Student student);

    boolean deleteById(String id);

    Optional<Student> findById(String id);

    List<Student> findAll();

    List<Student> searchByName(String keyword);
}
