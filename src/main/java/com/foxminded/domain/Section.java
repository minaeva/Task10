package com.foxminded.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static com.foxminded.domain.Validator.*;

@Data
public class Section {

    private long id;
    private String sectionName;
    private List<StudentMarks> studentMarks = new ArrayList<>();

    public Section(String subjectName){
        this.sectionName = subjectName;
    }

    public StudentMarks createStudentMarks(String studentName){
        if (StringUtils.isBlank(studentName)) {
            throw new IllegalArgumentException("Student cannot be empty");
        }
        StudentMarks newStudentMarks = new StudentMarks(studentName, this.sectionName);
        studentMarks.add(newStudentMarks);
        return newStudentMarks;
    }

    public StudentMarks findStudentMarks(String studentName){
        return findObjectByNameIfExists(studentMarks,
                studentMarks -> Objects.equals(studentMarks.getStudentName(), studentName),
                "Student marks",
                studentName);
    }

    public boolean removeStudentMarks(String studentName){
        return studentMarks.removeIf(marks -> Objects.equals(marks.getStudentName(), studentName));
    }

    public void addMark(String studentName, int mark){
        StudentMarks marks = findStudentMarks(studentName);
        marks.addMark(mark);
    }

    public double calculateAverageMark() {
        if (studentMarks.size() == 0) {
            return 0;
        }
        double result = 0.0;
        int counter = 0;
        for (StudentMarks marks: studentMarks) {
            double midAverage = marks.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        return (result == 0) ? 0 : result/counter;
    }
}