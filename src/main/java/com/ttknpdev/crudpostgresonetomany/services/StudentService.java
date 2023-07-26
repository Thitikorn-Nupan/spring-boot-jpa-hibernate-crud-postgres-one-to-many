package com.ttknpdev.crudpostgresonetomany.services;

import java.util.List;
import java.util.Map;

public interface StudentService <T> {
    List<T> reads();
    T read(Long code);
    T create (T obj);
    Map<String , T> update(T obj , Long code);
    Map<String , T> delete( Long code);
}
