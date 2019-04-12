package com.foxminded.domain;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.impl.GroupDaoImpl;
import com.foxminded.dao.impl.StudentDaoImpl;
import com.foxminded.dao.impl.TeacherDaoImpl;
import com.foxminded.dao.impl.AuditoriumDaoImpl;
import com.foxminded.dao.impl.SubjectDaoImpl;
import com.foxminded.dao.impl.DayScheduleDaoImpl;

import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;
import com.foxminded.model.TeacherCard;
import com.foxminded.model.Auditorium;
import com.foxminded.model.Subject;
import  com.foxminded.model.DaySchedule;
import java.util.*;

public class Faculty {

    private GroupDaoImpl groupDao = new GroupDaoImpl();
    private StudentDaoImpl studentDao = new StudentDaoImpl();
    private TeacherDaoImpl teacherDao = new TeacherDaoImpl();
    private AuditoriumDaoImpl auditoriumDao = new AuditoriumDaoImpl();
    private SubjectDaoImpl subjectDao = new SubjectDaoImpl();
    private DayScheduleDaoImpl dayScheduleDao = new DayScheduleDaoImpl();

    public Group createGroup(Group group, long facultyId){
        Group createdGroup = groupDao.create(group);
        return groupDao.addFacultyId(createdGroup, facultyId);
    }

    public Group updateGroup(Group group, String newGroupName) {
        group.setName(newGroupName);
        return groupDao.update(group);
    }

    public boolean dismantleGroup(Group group){
        try {
            groupDao.delete(group);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }

    public Group findGroup(Group group) {
        return groupDao.findById(group.getId());
    }

    public StudentCard takeStudent(StudentCard student, Group group){
        StudentCard newStudent = studentDao.create(student);
        return studentDao.addGroupId(student, group.getId());
    }

    public StudentCard changeStudentGroup(StudentCard student, Group group) {
        return studentDao.addGroupId(student, group.getId());
    }

    private StudentCard findStudent(StudentCard student){
        return studentDao.findById(student.getId());
    }

    public List<StudentCard> getAllStudents(){
        List<StudentCard> students = new ArrayList<>();
        List<Group> groups = groupDao.findAll();

        for (Group group: groups){
            students.addAll(studentDao.findAll());
        }
        return students;
    }

    public boolean dismissStudent(StudentCard student){
        try {
            studentDao.delete(student);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }

    public List<DaySchedule> createSchedule(long facultyId) {
        return dayScheduleDao.findAllByFacultyId(facultyId);
    }

    public TeacherCard hireTeacher(TeacherCard teacher, long facultyId, long subjectId){
        TeacherCard newTeacher = teacherDao.create(teacher);
        teacherDao.addFacultyId(newTeacher, facultyId);
        return teacherDao.addSubjectId(newTeacher, subjectId);
    }

    public TeacherCard findTeacher(TeacherCard teacher) {
        return teacherDao.findById(teacher.getId());
    }

    public boolean fireTeacher(TeacherCard teacher) {
        try {
            teacherDao.delete(teacher);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }

    public Auditorium addAuditorium(Auditorium auditorium, long facultyId){
        Auditorium newAuditorium = auditoriumDao.create(auditorium);
        return auditoriumDao.addFacultyId(newAuditorium, facultyId);
    }

    public boolean removeAuditorium(Auditorium auditorium) {
        try {
            auditoriumDao.delete(auditorium);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }

    public Auditorium findAuditorium(Auditorium auditorium){
        return auditoriumDao.findById(auditorium.getId());
    }

    public Subject addSubject(Subject subject, long facultyId){
        Subject newSubject = subjectDao.create(subject);
        return subjectDao.addFacultyId(newSubject, facultyId);
    }

    public boolean removeSubject(Subject subject){
        try {
            subjectDao.delete(subject);
        } catch (DaoException e) {
            return false;
        }
        return true;
    }

    public Subject findSubject(Subject subject){
        return subjectDao.findById(subject.getId());
    }
}
