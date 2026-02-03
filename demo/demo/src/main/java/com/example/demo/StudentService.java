package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll(); 
    }

    public Student getStudentById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteStudent(String id) {
        repository.deleteById(id);
    }

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> searchByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}