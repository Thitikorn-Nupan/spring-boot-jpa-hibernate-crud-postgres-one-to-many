package com.ttknpdev.crudpostgresonetomany.daos;

import com.ttknpdev.commonsresponsettknpdev.exception.handler.NotAllowedMethod;
import com.ttknpdev.crudpostgresonetomany.entities.Student;
import com.ttknpdev.crudpostgresonetomany.log.Logging;
import com.ttknpdev.crudpostgresonetomany.repositories.StudentRepository;
import com.ttknpdev.crudpostgresonetomany.repositories.SubjectRepository;
import com.ttknpdev.crudpostgresonetomany.services.StudentService;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service
public class DaoStudent extends Logging implements StudentService<Student> {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public DaoStudent(StudentRepository studentRepository , SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }
    @Override
    public List<Student> reads() {
        List<Student> response = new ArrayList<>();
        studentRepository.findAll().forEach(response::add);
        if (response.isEmpty()) {
            daoStudent.log(Level.WARN,"there are no fields in students table");
            throw new NotAllowedMethod("there are no fields in students table");
        }
        return response;
    }

    @Override
    public Student read(Long code) {
        return studentRepository.findById(code)
                .orElseThrow(()-> {
                    daoStudent.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }

    @Override
    public Student create(Student obj) {
        return studentRepository.save(obj);
    }


    @Override
    public Map<String, Student> update(Student obj , Long code) {
        return studentRepository.findById(code)
                .map(student -> {
                    Map<String , Student> response = new HashMap<>();
                    student.setFullname(obj.getFullname());
                    student.setAge(obj.getAge());
                    student.setStatus(obj.getStatus());
                    student.setWeight(obj.getWeight());
                    student.setHeight(obj.getHeight());
                    studentRepository.save(student);
                    response.put("updated" , student);
                    return  response;
                })
                .orElseThrow(()->{
                    daoStudent.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }


    @Override
    public Map<String, Student> delete(Long code) {
        return studentRepository.findById(code)
                .map(student -> {
                    Map<String , Student> response = new HashMap<>();
                    subjectRepository.deleteByForeignKey(code);
                    studentRepository.delete(student);
                    response.put("deleted",student);
                    return response;
                }).orElseThrow(()-> {
                    daoStudent.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }
}
