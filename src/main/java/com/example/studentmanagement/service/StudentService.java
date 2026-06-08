package com.example.studentmanagement.service;

import com.example.studentmanagement.model.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (findById(student.getId()).isPresent()) {
            return false;
        }
        students.add(student);
        return true;
    }

    public boolean updateStudent(String id, Student updatedStudent) {
        Optional<Student> existing = findById(id);
        if (existing.isEmpty()) {
            return false;
        }

        Student student = existing.get();
        student.setName(updatedStudent.getName());
        student.setAge(updatedStudent.getAge());
        student.setClassName(updatedStudent.getClassName());
        student.setGpa(updatedStudent.getGpa());
        return true;
    }

    public boolean deleteStudent(String id) {
        return students.removeIf(student -> student.getId().equalsIgnoreCase(id));
    }

    public Optional<Student> findById(String id) {
        return students.stream()
                .filter(student -> student.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public List<Student> searchByName(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return students.stream()
                .filter(student -> student.getName().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public List<Student> sortByGpaDesc() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .toList();
    }
}
