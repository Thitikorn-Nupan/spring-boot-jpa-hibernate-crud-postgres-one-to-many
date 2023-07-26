package com.ttknpdev.crudpostgresonetomany.repositories;

import com.ttknpdev.crudpostgresonetomany.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> { }
