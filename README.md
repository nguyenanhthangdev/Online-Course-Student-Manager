# Online Course Student Manager

**Online Course Student Manager** là một ứng dụng web nhỏ dùng để quản lý **sinh viên**, **khóa học**, và **việc đăng ký học**. Mục tiêu là giúp em thực hành các công nghệ như Spring Boot, Thymeleaf, JPA, và MySQL qua một dự án thực tế.

---

## Tính năng

- ✅ Quản lý danh sách sinh viên (thêm, sửa, xóa)
- ✅ Quản lý danh sách khóa học (thêm, sửa, xóa)
- ✅ Sinh viên đăng ký khóa học
- ✅ Xem sinh viên đã đăng ký những môn nào
- ✅ Xem môn học có những sinh viên nào đăng ký
- ✅ Kiểm tra trùng khi đăng ký cùng khóa học 2 lần
- ✅ Valid dữ liệu người dùng nhập vào (họ tên, email, ngày sinh,...)
- ✅ Thông báo lỗi rõ ràng, dễ hiểu
- ✅ Giao diện đơn giản, dễ dùng

---

## Công nghệ sử dụng

- **Java 21**
- **Spring Boot 3.5.x**
- **Spring MVC + Thymeleaf**
- **Spring Data JPA**
- **MySQL**
- **Bootstrap** (để làm giao diện)
- **Maven** để quản lý thư viện

---

## Cách chạy dự án

**Clone dự án về máy:**

```bash
git clone https://github.com/nguyenanhthangdev/online-course-student-manager.git
cd online-course-student-manager
```

---

## Cấu hình file application.properties:

```bash
- server.port = 8084
- spring.datasource.url=jdbc:mysql://localhost:3306/onlinecoursestudentmanager
- spring.datasource.username=root
- spring.datasource.password=

- spring.jpa.hibernate.ddl-auto=update
- spring.thymeleaf.cache=false
```

## Chạy app:

```bash
./mvnw spring-boot:run
```

Truy cập trình duyệt tại:

```bash
http://localhost:8084/students
```
