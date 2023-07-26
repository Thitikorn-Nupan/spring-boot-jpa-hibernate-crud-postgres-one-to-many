package com.ttknpdev.crudpostgresonetomany.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    @NotBlank
    private String fullname;
    @NotNull
    @Min(0)
    @Max(1000)
    private Float weight;
    @NotNull
    @Min(0)
    @Max(300)
    private Float height;
    @NotNull
    @Min(0)
    @Max(100)
    private Short age;
    @NotNull
    private Boolean status;
    /* One to Manny */
    @OneToMany(cascade = CascadeType.ALL,targetEntity = Subject.class)
    @JoinColumn(name = "st_code" , referencedColumnName = "code")
    private List<Subject> subjects;

    public Student(String fullname, Float weight, Float height, Short age, Boolean status, List<Subject> subjects) {
        this.fullname = fullname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.status = status;
        this.subjects = subjects;
    }

    public Student(String fullname, Float weight, Float height, Short age, Boolean status) {
        this.fullname = fullname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.status = status;
    }
}
