# Student Management

Project Java cơ bản theo OOP để quản lý sinh viên.

## Chức năng

- Thêm sinh viên
- Cập nhật sinh viên
- Xóa sinh viên
- Tìm kiếm sinh viên theo tên
- Hiển thị danh sách sinh viên
- Sắp xếp theo GPA giảm dần

## Cấu trúc

- `model`: lớp dữ liệu `Student`
- `service`: xử lý logic quản lý danh sách
- `view`: menu console nhập/xuất
- `Main`: điểm chạy chương trình

## Chạy

```bash
mvn clean package
```

Sau đó chạy class `com.example.studentmanagement.Main`.

## MySQL JDBC

Ứng dụng sẽ thử kết nối MySQL trước. Nếu không kết nối được, nó tự chuyển sang lưu tạm trong bộ nhớ.

Tạo database và bảng:

```sql
CREATE DATABASE student_db;

USE student_db;

CREATE TABLE students (
    id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    class_name VARCHAR(50) NOT NULL,
    gpa DOUBLE NOT NULL
);
```

Sửa thông tin kết nối trong `src/main/java/com/example/studentmanagement/util/DbConfig.java`.
