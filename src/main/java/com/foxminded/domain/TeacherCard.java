package com.foxminded.domain;

import lombok.Data;

@Data
public class TeacherCard {

    private long id;
    private String name;
    private String subjectName;

    public TeacherCard(String name){
        this.name = name;
    }

    public void addMark(String studentName, String subjectName,
                        Journal journal, int mark){
        journal.addMark(studentName, subjectName, mark);
    }

}