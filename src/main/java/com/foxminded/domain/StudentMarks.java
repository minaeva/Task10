package com.foxminded.domain;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentMarks {

    private String studentName;
    private String sectionName;
    private List<Integer> marks = new ArrayList<>();

    public StudentMarks(String studentName, String sectionName){
        this.studentName = studentName;
        this.sectionName = sectionName;
    }

    public double calculateAverageMark() {
        if (marks.size() == 0) {
            return 0;
        }
        double result =  0.0;
        int counter = 0;
        for(Integer mark: marks){
            result += mark;
            counter++;
        }
        return (result == 0) ? 0 : result/counter;
    }

    public void addMark(int mark) throws IllegalArgumentException{
        if ((mark < 1) || (mark > 12)) {
            throw new IllegalArgumentException("Mark should be [1;12]");
        }
        marks.add(mark);
    }
}
