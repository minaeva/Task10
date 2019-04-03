package com.foxminded.domain;

import java.util.*;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import static com.foxminded.domain.Validator.*;

@Data
public class Faculty {

    private String name;
    private List<Group> groups = new ArrayList<>();
    private List<MentorCard> mentors = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Auditorium> auditoria = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();
    private Schedule schedule;

    public Faculty(String name) {
        this.name = name;
    }

    public Group createGroup(String groupName){
        if (StringUtils.isBlank(groupName)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        validateNameIsUnique(groups,
                group -> Objects.equals(group.getName(), groupName),
                "Group",
                groupName);
        Group newGroup = new Group(groupName);
        groups.add(newGroup);
        return newGroup;
    }

    public Group updateGroup(String groupName, String newGroupName) {
        Group group = findGroup(groupName);
        validateNameIsUnique(groups,
                groupToCheck -> Objects.equals(groupToCheck.getName(), newGroupName),
                "Group",
                name);
        group.setName(newGroupName);
        return group;
    }

    public boolean dismantleGroup(String groupName){
        return groups.removeIf(group -> Objects.equals(group.getName(), groupName));
    }

    public Group findGroup(String groupName) {
        return findObjectByNameIfExists(groups,
                group -> Objects.equals(group.getName(), groupName),
                "Group",
                groupName);
    }

    public StudentCard takeStudent(String studentName, String groupName){
        if (StringUtils.isBlank(studentName)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Group group = findGroup(groupName);
        StudentCard newStudent = new StudentCard(studentName);
        group.takeStudent(newStudent);
        return newStudent;
    }

    public StudentCard changeStudentGroup(String studentName, String newGroupName) {
        Group newGroup = findGroup(newGroupName);
        return newGroup.takeStudent(findStudent(studentName));
    }

    private StudentCard findStudent(String studentName){
        return findObjectByNameIfExists(getAllStudents(),
                student -> Objects.equals(student.getName(), studentName),
                "Student",
                studentName);
    }

    public List<StudentCard> getAllStudents(){
        List<StudentCard> students = new ArrayList<>();
        for (Group group: groups){
            students.addAll(group.getStudents());
        }
        return students;
    }

    public void dismissStudent(String studentName){
        for (Group group: groups) {
            group.dismissStudent(studentName);
        }
    }

    public Schedule createSchedule() {
        Schedule newSchedule = new Schedule();
        this.schedule = newSchedule;
        return newSchedule;
    }

    public void clearSchedule() {
        this.schedule = null;
    }

    public MentorCard hireMentor(String mentorName){
        if (StringUtils.isBlank(mentorName)){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        MentorCard newMentor = new MentorCard(mentorName);
        mentors.add(newMentor);
        return newMentor;
    }

    public MentorCard findMentor(String mentorName) {
        return findObjectByNameIfExists(mentors,
                mentor -> Objects.equals(mentor.getName(), mentorName),
                "Mentor",
                mentorName);
    }

    public boolean fireMentor(String mentorName) {
        return mentors.removeIf(mentor -> Objects.equals(mentor.getName(), mentorName));
    }

    public Auditorium addAuditorium(int auditoriumNumber){
        if (auditoriumNumber <= 0) {
            throw new IllegalArgumentException("Number should be positive");
        }
        Auditorium auditorium = new Auditorium(auditoriumNumber);
        auditoria.add(auditorium);
        return auditorium;
    }

    public boolean removeAuditorium(int auditoriumNumber) {
        return auditoria.removeIf(auditorium -> Objects.equals(auditorium.getNumber(), auditoriumNumber));
    }

    public Auditorium findAuditorium(int auditoriumNumber){
        return findObjectByNumberIfExists(auditoria,
                auditorium -> Objects.equals(auditorium.getNumber(), auditoriumNumber),
                "Auditorium",
                auditoriumNumber);
    }

    public Subject addSubject(String subjectName){
        if (StringUtils.isBlank(subjectName)){
            throw new IllegalArgumentException("Subject cannot be empty");
        }
        Subject subject = new Subject(subjectName);
        subjects.add(subject);
        return subject;
    }

    public boolean removeSubject(String subjectName){
        return subjects.removeIf(subject -> Objects.equals(subject.getName(), subjectName));
    }

    public Subject findSubject(String subjectName){
        return findObjectByNameIfExists(subjects,
                subject -> Objects.equals(subject.getName(), subjectName),
                "Subject",
                subjectName);
    }

    public double calculateAverageMark() {
        if (groups.size() == 0) {
            return 0;
        }
        double result = 0.0;
        int counter = 0;
        for (Group group: groups) {
            double midAverage = group.getJournal().calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        return (result == 0) ? 0 : result/counter;
    }
}