package com.foxminded.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(generator = "group_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @EqualsAndHashCode.Exclude
    @OneToMany
    @JoinColumn(name = "group_id")
    private List<StudentCard> students = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + "]";
    }
}
