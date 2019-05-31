package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "students")
@Table(name = "students")
public class StudentCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = false, nullable = false, length = 100)
    private String name;

/*
    @ManyToOne
    private Group group;
*/

    public StudentCard(String name){
        this.name = name;
    }
}
