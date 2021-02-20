package controllers;

import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String home() {
        return "home is called!";
    }

    @GetMapping("/get/students")
    public List<Student> getAllStudents() {
        return studentService.getStudents();
    }

    @PostMapping("/post/save")
    public void registerNewStudent(@RequestBody Student student) {
        studentService.createStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable("id") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(id, name, email);
    }
}
