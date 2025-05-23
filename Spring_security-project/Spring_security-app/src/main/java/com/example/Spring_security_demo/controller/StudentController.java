package com.example.Spring_security_demo.controller;


import com.example.Spring_security_demo.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    List<Student> students=new ArrayList<>(List.of(
            new Student(1, "Ujjwal","java"),
            new Student(2,"david","Python"),
            new Student(3,"DIBYA","DEVOPS")



    ));

    @GetMapping("crftoken")
    public CsrfToken getcsrftoken(HttpServletRequest request){
        return (CsrfToken)  request.getAttribute("_csrf");
    }



    @GetMapping("students")
    public List<Student> getStudents(){
        return students;
    }

    @PostMapping("students")
    public void addstudents(@RequestBody Student student){
        students.add(student);
    }

}
