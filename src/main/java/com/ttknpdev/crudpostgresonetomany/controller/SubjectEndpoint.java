package com.ttknpdev.crudpostgresonetomany.controller;

import com.ttknpdev.commonsresponsettknpdev.constants.CommonsConstants;
import com.ttknpdev.commonsresponsettknpdev.response.CommonsResponseObject;
import com.ttknpdev.crudpostgresonetomany.entities.Student;
import com.ttknpdev.crudpostgresonetomany.entities.Subject;
import com.ttknpdev.crudpostgresonetomany.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/subject")
public class SubjectEndpoint {
    private SubjectService<Subject> subjectSubjectService;
    @Autowired
    public SubjectEndpoint(SubjectService<Subject> subjectSubjectService) {
        this.subjectSubjectService = subjectSubjectService;
    }
    @PostMapping(value = "/create/{code}")
    private ResponseEntity create(@RequestBody Subject subject , @PathVariable Long code) {
        return ResponseEntity.ok(CommonsResponseObject
                .<Subject>builder()
                .status(CommonsConstants.STATUS_CREATED)
                .code(CommonsConstants.STRING_CREATED)
                .info(subjectSubjectService.create(subject,code))
                .build());
    }

    @GetMapping(value = "/reads/{code}")
    private ResponseEntity reads(@PathVariable Long code) {
        return ResponseEntity.ok(CommonsResponseObject
                .<List<Subject>>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(subjectSubjectService.reads(code))
                .build());
    }

    @PutMapping(value = "/update/{code}/{no}")
    private ResponseEntity update(@PathVariable Long code , @PathVariable Long no , @RequestBody Subject subject) {
        return ResponseEntity.ok(CommonsResponseObject
                .<Map<String,Subject>>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(subjectSubjectService.update(subject,no,code))
                .build());
    }
    @DeleteMapping(value = "/delete/{code}/{no}")
    private ResponseEntity delete(@PathVariable Long code , @PathVariable Long no ) {
        return ResponseEntity.ok(CommonsResponseObject
                .<Map<String,Subject>>builder()
                .status(CommonsConstants.STATUS_ACCEPTED)
                .code(CommonsConstants.STRING_ACCEPTED)
                .info(subjectSubjectService.delete(no,code))
                .build());
    }
}
