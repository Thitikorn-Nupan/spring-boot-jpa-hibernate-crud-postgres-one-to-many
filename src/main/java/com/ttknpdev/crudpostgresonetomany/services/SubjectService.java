package com.ttknpdev.crudpostgresonetomany.services;

import com.ttknpdev.crudpostgresonetomany.entities.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectService <T>{
    T create(T obj , Long code);
    List<T> reads(Long code);
    Map<String, Subject> update(T obj,Long no,Long code);
    Map<String, Subject> delete(Long no,Long code);
}
