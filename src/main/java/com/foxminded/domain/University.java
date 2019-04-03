package com.foxminded.domain;

import java.util.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import static com.foxminded.domain.Validator.*;

@Data
public class University {

    private String name;
    private List<Faculty> faculties = new ArrayList<>();

    public Faculty createFaculty(String facultyName){
        if (StringUtils.isBlank(facultyName)){
            throw new IllegalArgumentException("Faculty name cannot be empty");
        }
        validateNameIsUnique(faculties,
                faculty -> faculty.getName().equals(facultyName),
                "Faculty",
                name);
        Faculty newFaculty = new Faculty(facultyName);
        faculties.add(newFaculty);
        return newFaculty;
    }

    public Faculty updateFaculty(String facultyName, String newGroupName){
        Faculty faculty = findFaculty(facultyName);
        validateNameIsUnique(faculties,
                facultyToCheck -> Objects.equals(facultyToCheck.getName(), newGroupName),
                "Faculty",
                name);
        faculty.setName(newGroupName);
        return faculty;
    }

    public boolean dismantleFaculty(String facultyName){
        return faculties.removeIf(faculty -> Objects.equals(faculty.getName(), facultyName));
    }

    public Faculty findFaculty(String facultyName){
        return findObjectByNameIfExists(faculties,
                faculty -> Objects.equals(faculty.getName(), facultyName),
                "Faculty",
                facultyName);
    }

    public double calculateAverageMark(){
        double result = 0.0;
        int counter = 0;
        for (Faculty faculty: faculties) {
            double midAverage = faculty.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        return result/counter;
    }
}