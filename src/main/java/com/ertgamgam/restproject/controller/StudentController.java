package com.ertgamgam.restproject.controller;

import com.ertgamgam.restproject.model.Student;
import com.ertgamgam.restproject.repository.StudentRepo;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/students"})
public class StudentController {

    @Autowired //with this annotation, don't need constructor injection :)
    private StudentRepo studentRepo;

    /* public StudentController(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    } */




     /* @GetMapping
    public List findAll() {
        studentRepo.findAll()
        return studentRepo.findAll();
    } */

    @GetMapping(path = "/findbysurnameandstudentno")
    public List test(@RequestParam(value = "surname", defaultValue = "GAMGAM", required = false) String surname,
                     @RequestParam(value="studentno")int studentNo) {
        var responseList = studentRepo.findBySurnameAndStudentNo(surname,studentNo);
        return responseList;
    }

    //http://localhost:8282/students?page=1&size=3&sort=id,DESC
    @GetMapping
    public Page<Student> findAll(Pageable pageable) {
        studentRepo.findAll(pageable);
        return studentRepo.findAll(pageable);
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

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return studentRepo.findById(id)
                .map(record -> {
                    studentRepo.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
