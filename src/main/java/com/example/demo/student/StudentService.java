package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**-----Service Layer-----*/
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
       Optional<Student>studentOptional= studentRepository.findStudentByEmail(student.getEmail());
       if (studentOptional.isPresent()){
           throw new IllegalStateException("email taken");
       }
        studentRepository.save(student);
        System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
      boolean exists=  studentRepository.existsById(studentId);
      if (!exists){
          throw new IllegalStateException("student with id "+studentId+" does not exists");
      }
      studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String emil) {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException(
                        "student with id "+studentId+" does not exists"
                ));

        if (name!=null && name.length()>0 &&
                !Objects.equals(student.getName(),name)){
               student.setName(name);
        }
        if (emil!=null && emil.length()>0 &&
                !Objects.equals(student.getEmail(),emil)){
            Optional<Student>studentOptional=studentRepository.findStudentByEmail(emil);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(emil);
        }


    }
}
