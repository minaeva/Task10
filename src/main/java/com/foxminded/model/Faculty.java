package com.foxminded.model;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

    @Data
    public class Faculty {

        private long id;
        private String name;
        private List<Group> groups = new ArrayList<>();
        private List<TeacherCard> teachers = new ArrayList<>();
        private List<Subject> subjects = new ArrayList<>();
        private List<Auditorium> auditoria = new ArrayList<>();
        private Schedule schedule;

        public Faculty(String name) {
            this.name = name;
        }

        public Group createGroup(String groupName){
            if (StringUtils.isBlank(groupName)){
                throw new IllegalArgumentException("Name cannot be empty");
            }
            Group newGroup = new Group(groupName);
            groups.add(newGroup);
            return newGroup;
        }

        public Group updateGroup(String groupName, String newGroupName) {
            Group group = findGroup(groupName);
            group.setName(newGroupName);
            return group;
        }

        public Group findGroup(String groupName) {
            for (Group group: groups) {
                if (group.getName().equals(groupName)) {
                    return group;
                }
            }
            return null;
        }

        public boolean dismantleGroup(String groupName){
            for (Group group: groups) {
                if (group.getName().equals(groupName)) {
                    groups.remove(group);
                    return true;
                }
            }
            return false;
        }

         public StudentCard takeStudent(String studentName, String groupName){
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
            List<StudentCard> allStudents = getAllStudents();
            for (StudentCard student: allStudents) {
                if (student.getName().equals(studentName)) {
                    return student;
                }
            }
            return null;
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
                if (group.dismissStudent(studentName)){
                    return;
                }
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

        public TeacherCard hireTeacher(String teacherName){
            if (StringUtils.isBlank(teacherName)){
                throw new IllegalArgumentException("Name cannot be empty");
            }
            TeacherCard newTeacher = new TeacherCard(teacherName);
            teachers.add(newTeacher);
            return newTeacher;
        }

        public TeacherCard findTeacher(String teacherName) {
            for (TeacherCard teacher: teachers) {
                if (teacher.getName().equals(teacherName)) {
                    return teacher;
                }
            }
            return null;
        }

        public boolean fireTeacher(String teacherName) {
            for (TeacherCard teacher: teachers) {
                if (teacher.getName().equals(teacherName)) {
                    teachers.remove(teacher);
                    return true;
                }
            }
            return false;
        }

        public Auditorium addAuditorium(int auditoriumNumber){
            if (auditoriumNumber <= 0) {
                throw new IllegalArgumentException("Number should be positive");
            }
            Auditorium auditorium = new Auditorium(auditoriumNumber);
            auditoria.add(auditorium);
            return auditorium;
        }

        public Auditorium findAuditorium(int auditoriumNumber){
            for (Auditorium auditorium: auditoria) {
                if (auditorium.getNumber() == auditoriumNumber) {
                    return auditorium;
                }
            }
            return null;
        }

        public boolean removeAuditorium(int auditoriumNumber) {
            for (Auditorium auditorium: auditoria) {
                if (auditorium.getNumber() == auditoriumNumber) {
                    auditoria.remove(auditorium);
                    return true;
                }
            }
            return false;
        }

        public Subject addSubject(String subjectName){
            if (StringUtils.isBlank(subjectName)){
                throw new IllegalArgumentException("Subject cannot be empty");
            }
            Subject subject = new Subject(subjectName);
            subjects.add(subject);
            return subject;
        }

        public Subject findSubject(String subjectName){
            for (Subject subject: subjects) {
                if (subject.getName().equals(subjectName)) {
                    return subject;
                }
            }
            return null;
        }

        public boolean removeSubject(String subjectName){
            for (Subject subject: subjects) {
                if (subject.getName().equals(subjectName)) {
                    subjects.remove(subject);
                    return true;
                }
            }
            return false;
        }
    }

