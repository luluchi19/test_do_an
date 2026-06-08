package com.example.studentmanagement.repository;

import com.example.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    @Override
    public boolean insert(Student student) {
        if (findById(student.getId()).isPresent()) {
            return false;
        }
        students.add(student);
        return true;
    }

    @Override
    public boolean update(Student student) {
        Optional<Student> existing = findById(student.getId());
        if (existing.isEmpty()) {
            return false;
        }

        Student current = existing.get();
        current.setName(student.getName());
        current.setAge(student.getAge());
        current.setClassName(student.getClassName());
        current.setGpa(student.getGpa());
        return true;
    }

    @Override
    public boolean deleteById(String id) {
        return students.removeIf(student -> student.getId().equalsIgnoreCase(id));
    }

    @Override
    public Optional<Student> findById(String id) {
        return students.stream()
                .filter(student -> student.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    @Override
    public List<Student> searchByName(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }
}
