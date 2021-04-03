package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * -----Api Layer----
 **/
@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
       return studentService.getStudents();
    }


    @PostMapping(path = "/registerNewStudent")
    public void registerNewStudent(@RequestBody Student student){
       studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId")Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId")Long studentId,
                              @RequestParam(required = false)String name,
                              @RequestParam(required = false)String emil){
        studentService.updateStudent(studentId,name,emil);

    }
}


