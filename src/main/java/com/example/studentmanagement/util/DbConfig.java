package com.example.studentmanagement.util;

public final class DbConfig {
    private DbConfig() {
    }

    public static final String URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh";
    public static final String USER = "root";
    public static final String PASSWORD = "password";
}
