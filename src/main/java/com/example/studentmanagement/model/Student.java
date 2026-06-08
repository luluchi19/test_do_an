package com.example.studentmanagement.model;

public class Student {
    private String id;
    private String name;
    private int age;
    private String className;
    private double gpa;

    public Student(String id, String name, int age, String className, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.className = className;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-5d %-15s %-5.2f", id, name, age, className, gpa);
    }
}
