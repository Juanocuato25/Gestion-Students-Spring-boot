package com.example.demo.controller;


import com.example.demo.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {


    List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Juan Camilo Arenas", "juancarenas2@hotmail.com", 26, "11A"),
            new Student(2, "Nicolas Ortiz Chaparro", "nicortiz@gmail.com", 26, "11B"),
            new Student(3, "Oscar Montero", "oscarm@gmail.com", 26, "11C"),
            new Student(4, "Estiven Garcia", "esgarcia@hotmail.com", 26, "11D")
    ));


    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    @GetMapping("/{email}")
    public Student getStudentsByEmail(@PathVariable String email) {
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) return s;
        }
        return null;
    }

    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    @PutMapping
    public Student putStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getID() == student.getID()) {
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());

                return s;
            }
        }
        return null;
    }

    @PatchMapping
    public Student pathStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getID() == student.getID()) {
                if (student.getName() != null) {
                    s.setName(student.getName());
                }
                if (student.getEmail() != null) {
                    s.setEmail(student.getEmail());
                }
                if (student.getAge() != 0) {
                    s.setAge(student.getAge());
                }
                if (student.getCourse() != null) {
                    s.setCourse(student.getCourse());
                }
                return s;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable int id) {
        for (Student s : students) {
            if (s.getID() == id) {
                students.remove(s);
                return s;
            }
        }
        return null;
    }
}
