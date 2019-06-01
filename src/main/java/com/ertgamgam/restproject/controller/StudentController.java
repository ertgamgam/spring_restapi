package com.ertgamgam.restproject.controller;

import com.ertgamgam.restproject.model.Student;
import com.ertgamgam.restproject.repository.StudentRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/students"})
public class StudentController {

    private StudentRepo studentRepo;

    public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @GetMapping
    public List findAll() {
        return studentRepo.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Student> findById(@PathVariable long id) {
        return studentRepo.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentRepo.save(student);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") long id,
                                          @RequestBody Student student) {
        return studentRepo.findById(id)
                .map(record -> {
                    record.setName(student.getName());
                    record.setSurname(student.getSurname());
                    Student updated = studentRepo.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return studentRepo.findById(id)
                .map(record -> {
                    studentRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
