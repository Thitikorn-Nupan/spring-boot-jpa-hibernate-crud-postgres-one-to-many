package com.ttknpdev.crudpostgresonetomany.repositories;

import com.ttknpdev.crudpostgresonetomany.entities.Subject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject , Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from subjects where st_code = :code",nativeQuery = true)
    void deleteByForeignKey(@Param("code") Long code);

    @Modifying
    @Transactional
    @Query(value = "insert into subjects(fullname,score,grade,st_code) values (:fullname , :score , :grade, :code) ",nativeQuery = true)
    void createToForeignKey(@Param("fullname") String fullname ,@Param("score") Short score ,@Param("grade") String grade ,@Param("code") Long code);

    @Query(value = "select * from subjects where st_code = :code",nativeQuery = true)
    List<Subject> readsByForeignKey(@Param("code") Long code);
}
