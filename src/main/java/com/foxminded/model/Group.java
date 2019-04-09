package com.foxminded.model;

import lombok.Data;
import java.util.*;

@Data
public class Group {

    private long id;
    private String name;
    private List<StudentCard> students = new ArrayList<>();

    public Group(String name){
        this.name = name;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.add(studentCard);
        studentCard.setGroupName(this.name);
        return studentCard;
    }

    public StudentCard findStudent(String studentName){
        for (StudentCard student: students) {
            if (student.getName().equals(studentName)) {
                return student;
            }
        }
        return null;
    }

    public boolean dismissStudent(String studentName){
        for (StudentCard student: students){
            if (student.getName().equals(studentName)){
                students.remove(student);
                return true;
            }
        }
        return false;
    }
}
