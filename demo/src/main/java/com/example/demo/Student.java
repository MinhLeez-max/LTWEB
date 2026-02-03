package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(length = 50) // Giới hạn độ dài UUID cho gọn
    private String id; 

    @Column(name = "name", columnDefinition = "NVARCHAR(255)") 
    private String name;

    private int age;
    private String email;
    
    @Column(name = "gender", columnDefinition = "NVARCHAR(10)")
    private String gender; 

    // Constructor mặc định TRỐNG (Cực kỳ quan trọng cho Hibernate/Spring Form)
    public Student() {
    }

    // Phương thức tiền xử lý trước khi lưu vào DB
    // Chỉ tạo ID mới nếu ID hiện tại đang null (dùng cho Thêm mới)
    // Nếu ID đã có (dùng cho Sửa), nó sẽ giữ nguyên ID cũ
    public void generateIdIfNull() {
        if (this.id == null || this.id.trim().isEmpty()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    // --- Getter và Setter (Giữ nguyên) ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
}