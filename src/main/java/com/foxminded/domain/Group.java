package com.foxminded.domain;

import lombok.Data;
import java.util.*;
import static com.foxminded.domain.Validator.*;

@Data
public class Group {

    private String name;
    private List<StudentCard> students = new ArrayList<>();
    private Journal journal;

    public Group(String name){
        this.name = name;
        Journal createdJournal = new Journal();
        this.journal = createdJournal;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.add(studentCard);
        studentCard.setGroupName(this.name);
        return studentCard;
    }

    public StudentCard findStudent(String studentName){
        return findObjectByNameIfExists(students,
                student -> Objects.equals(student.getName(), studentName),
                "Student",
                studentName);
    }

    public boolean dismissStudent(String studentName){
        return students.removeIf(student -> Objects.equals(student.getName(), studentName));
    }
}