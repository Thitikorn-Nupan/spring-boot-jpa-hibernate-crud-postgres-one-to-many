package com.ttknpdev.crudpostgresonetomany.daos;

import com.ttknpdev.commonsresponsettknpdev.exception.handler.NotAllowedMethod;
import com.ttknpdev.crudpostgresonetomany.entities.Subject;
import com.ttknpdev.crudpostgresonetomany.log.Logging;
import com.ttknpdev.crudpostgresonetomany.repositories.StudentRepository;
import com.ttknpdev.crudpostgresonetomany.repositories.SubjectRepository;
import com.ttknpdev.crudpostgresonetomany.services.SubjectService;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DaoSubject extends Logging implements SubjectService<Subject> {

    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public DaoSubject(SubjectRepository subjectRepository , StudentRepository studentRepository) {
        this.subjectRepository = subjectRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Subject create(Subject obj, Long code) {
        return studentRepository.findById(code)
                .map(student -> {
                    subjectRepository.createToForeignKey(obj.getFullname(), obj.getScore(), obj.getGrade(),code);
                    return obj;
                })
                .orElseThrow(() -> {
                    daoSubject.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }

    @Override
    public List<Subject> reads(Long code) {
        return studentRepository.findById(code)
                .map(student -> {
                    List<Subject> subjects = new ArrayList<>();
                    subjects.addAll(subjectRepository.readsByForeignKey(code));
                    return subjects;
                })
                .orElseThrow(() -> {
                    daoSubject.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }

    @Override
    public Map<String, Subject> update(Subject obj, Long no, Long code) {
        return studentRepository.findById(code)
                .map(student -> {
                    Map<String , Subject> response = new HashMap<>();
                    subjectRepository.findById(no)
                            .ifPresent(subject -> {
                                subject.setFullname(obj.getFullname());
                                subject.setGrade(obj.getGrade());
                                subject.setScore(obj.getScore());
                                subjectRepository.save(subject);
                                response.put("updated",subject);
                            });
                return response;
                }).orElseThrow(() -> {
                    daoSubject.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }

    @Override
    public Map<String, Subject> delete(Long no, Long code) {
        return studentRepository.findById(code)
                .map(student -> {
                    Map<String , Subject> response = new HashMap<>();
                    subjectRepository.findById(no).ifPresent(subject -> {
                        subjectRepository.deleteById(no);
                        response.put("deleted",subject);
                    });
                    return response;
                }).orElseThrow(() -> {
                    daoSubject.log(Level.DEBUG,"there are no code "+code+" in students table");
                    return new NotAllowedMethod("there are no code " + code + " in students table");
                });
    }
}
