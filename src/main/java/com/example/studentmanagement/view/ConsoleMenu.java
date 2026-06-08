package com.example.studentmanagement.view;

import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final StudentService studentService;
    private final Scanner scanner;

    public ConsoleMenu(StudentService studentService) {
        this.studentService = studentService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            printMenu();
            int choice = readInt("Chọn: ");

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> searchStudent();
                case 5 -> listStudents();
                case 6 -> listStudentsSortedByGpa();
                case 0 -> {
                    System.out.println("Thoát chương trình.");
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("===== QUẢN LÝ SINH VIÊN =====");
        System.out.println("1. Thêm sinh viên");
        System.out.println("2. Cập nhật sinh viên");
        System.out.println("3. Xóa sinh viên");
        System.out.println("4. Tìm kiếm sinh viên theo tên");
        System.out.println("5. Hiển thị danh sách");
        System.out.println("6. Sắp xếp theo GPA giảm dần");
        System.out.println("0. Thoát");
    }

    private void addStudent() {
        Student student = readStudent(false);
        boolean added = studentService.addStudent(student);
        System.out.println(added ? "Đã thêm sinh viên." : "Mã sinh viên đã tồn tại.");
    }

    private void updateStudent() {
        String id = readString("Nhập mã sinh viên cần sửa: ");
        if (studentService.findById(id).isEmpty()) {
            System.out.println("Không tìm thấy sinh viên.");
            return;
        }

        Student updated = readStudentWithoutId();
        boolean success = studentService.updateStudent(id, updated);
        System.out.println(success ? "Đã cập nhật sinh viên." : "Cập nhật thất bại.");
    }

    private void deleteStudent() {
        String id = readString("Nhập mã sinh viên cần xóa: ");
        boolean success = studentService.deleteStudent(id);
        System.out.println(success ? "Đã xóa sinh viên." : "Không tìm thấy sinh viên.");
    }

    private void searchStudent() {
        String keyword = readString("Nhập tên cần tìm: ");
        List<Student> results = studentService.searchByName(keyword);
        printStudents(results);
    }

    private void listStudents() {
        printStudents(studentService.getAllStudents());
    }

    private void listStudentsSortedByGpa() {
        printStudents(studentService.sortByGpaDesc());
    }

    private void printStudents(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }

        System.out.printf("%-10s %-20s %-5s %-15s %-5s%n", "Mã", "Họ tên", "Tuổi", "Lớp", "GPA");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private Student readStudent(boolean includeId) {
        String id = includeId ? readString("Mã SV: ") : "";
        String name = readString("Họ tên: ");
        int age = readInt("Tuổi: ");
        String className = readString("Lớp: ");
        double gpa = readDouble("GPA: ");
        return new Student(id, name, age, className, gpa);
    }

    private Student readStudentWithoutId() {
        return readStudent(false);
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số thực hợp lệ.");
            }
        }
    }
}
