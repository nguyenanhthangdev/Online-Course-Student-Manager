# Sử dụng image JDK chính thức
FROM openjdk:17-jdk-slim

# Tạo thư mục làm việc
WORKDIR /app

# Copy file jar đã build vào container
COPY target/onlinecoursestudentmanager-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render thường dùng 10000)
EXPOSE 8080

# Lệnh chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
