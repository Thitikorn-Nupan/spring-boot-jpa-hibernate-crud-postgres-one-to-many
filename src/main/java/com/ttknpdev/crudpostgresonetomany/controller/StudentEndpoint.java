package com.ttknpdev.crudpostgresonetomany.controller;

import com.ttknpdev.commonsresponsettknpdev.constants.CommonsConstants;
import com.ttknpdev.commonsresponsettknpdev.response.CommonsResponseObject;
import com.ttknpdev.crudpostgresonetomany.entities.Student;
import com.ttknpdev.crudpostgresonetomany.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/student")
public class StudentEndpoint {

    private final StudentService<Student> studentService;

    @Autowired
    public StudentEndpoint(StudentService<Student> studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/reads")
    private ResponseEntity reads() {
        return ResponseEntity.ok(
                CommonsResponseObject
                .<List<Student>>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(studentService.reads())
                .build()
        );
    }

    @GetMapping(value = "/read/{code}")
    private ResponseEntity read(@PathVariable Long code) {
        return ResponseEntity.ok(
                CommonsResponseObject
                .<Student>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(studentService.read(code))
                .build()
        );
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody Student student) {
        return ResponseEntity.ok(
                CommonsResponseObject
                .<Student>builder()
                .status(CommonsConstants.STATUS_CREATED)
                .code(CommonsConstants.STRING_CREATED)
                .info(studentService.create(student))
                .build()
        );
    }

    @PutMapping(value = "/update/{code}")
    private ResponseEntity update(@RequestBody Student student , @PathVariable Long code) {
        return ResponseEntity.ok(
                CommonsResponseObject
                .<Map<String,Student>>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(studentService.update(student,code))
                .build()
        );
    }

    @DeleteMapping(value = "/delete/{code}")
    private ResponseEntity delete( @PathVariable Long code) {
        return ResponseEntity.ok(
                CommonsResponseObject
                .<Map<String,Student>>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(studentService.delete(code))
                .build()
        );
    }
}
