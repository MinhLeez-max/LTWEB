package com.example.demo;

public class Student {
    private int id;
    private String name;
    private int age;

    // Constructor để khởi tạo đối tượng nhanh
    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getter (Bắt buộc phải có để Spring Boot có thể chuyển Object sang JSON)
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    // Setter (Dùng khi cần thay đổi giá trị)
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
}