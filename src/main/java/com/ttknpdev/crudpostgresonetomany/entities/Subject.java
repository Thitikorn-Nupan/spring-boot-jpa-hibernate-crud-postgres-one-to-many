package com.ttknpdev.crudpostgresonetomany.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // @JsonIgnore use to ignore this field for showing the data
    private Long no;
    @NotBlank
    private String fullname;
    @NotNull
    private Short score;
    @NotBlank
    @Size(max = 2)
    private String grade;

    public Subject(String fullname, Short score, String grade) {
        this.fullname = fullname;
        this.score = score;
        this.grade = grade;
    }
}
