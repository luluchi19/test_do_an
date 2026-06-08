package com.example.studentmanagement;

import com.example.studentmanagement.service.StudentService;
import com.example.studentmanagement.view.ConsoleMenu;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        ConsoleMenu consoleMenu = new ConsoleMenu(studentService);
        consoleMenu.start();
    }
}
