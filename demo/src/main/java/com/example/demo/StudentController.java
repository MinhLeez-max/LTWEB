package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/") 
public class StudentController {

    @Autowired
    private StudentService studentService;

    // 1. Hiển thị danh sách sinh viên
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students"; 
    }

    // 2. Mở form thêm mới
    @GetMapping("/student/add")
    public String showAddForm(Model model) {
        // Tạo đối tượng trống, ID sẽ được xử lý lúc nhấn Save
        model.addAttribute("student", new Student());
        return "add-student"; 
    }

    // 3. Mở form sửa (Nạp dữ liệu cũ bằng ID)
    @GetMapping("/student/edit/{id}")
    public String showEditForm(@PathVariable("id") String id, Model model) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            model.addAttribute("student", student);
            return "add-student"; 
        }
        return "redirect:/students";
    }

    // 4. Xử lý Lưu (Cả Thêm và Sửa) - ĐÃ SỬA TẠI ĐÂY
    @PostMapping("/student/save")
    public String saveStudent(@ModelAttribute("student") Student student) {
        // GỌI HÀM TẠO UUID: 
        // Nếu là thêm mới (id null), nó sẽ tạo mới. 
        // Nếu là sửa (đã có id từ input hidden), nó sẽ giữ nguyên id đó để Update.
        student.generateIdIfNull(); 
        
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // 5. Xem chi tiết
    @GetMapping("/student/{id}")
    public String getStudentDetail(@PathVariable("id") String id, Model model) { 
        Student student = studentService.getStudentById(id); 
        model.addAttribute("student", student);
        return "student-detail";
    }

    // 6. Xóa
    @GetMapping("/student/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteStudent(@PathVariable("id") String id) { 
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Xóa thành công"); 
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống");
        }
    }

    // 7. API Search
    @GetMapping("/api/students/search")
    @ResponseBody
    public List<Student> searchStudents(@RequestParam(value = "name", required = false) String name) {
        if (name == null || name.isEmpty()) {
            return studentService.getAllStudents();
        }
        return studentService.searchByName(name);
    }
}