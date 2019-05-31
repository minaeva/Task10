package com.foxminded.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity(name = "groups")
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique = false, nullable = false, length = 100)
    private String name;

    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy="group")
    private List<StudentCard> students = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }
}
