package com.foxminded.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "students")
public class StudentCard {

    @Id
    @GeneratedValue(generator = "student_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public StudentCard(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + "]";
    }
}
