package com.foxminded.domain;

import com.foxminded.dao.*;
import com.foxminded.dao.impl.*;
import com.foxminded.model.*;
import java.util.List;

public class FacultyDomain {

    private FacultyDao facultyDao = new FacultyDaoImpl();

    public Group addGroup(Faculty faculty, Group group) {
        Group newGroup = GroupDomain.createGroup(group);

        faculty.getGroups().add(newGroup);

        facultyDao.addGroup(group.getId(), newGroup);

        return newGroup;
    }

    public Group updateGroup(Faculty faculty, Group group) {
        long oldFacultyId = findFacultyByGroup(group);
        Faculty oldFaculty = findFacultyById(oldFacultyId);

        oldFaculty.getGroups().remove(group);
        faculty.getGroups().add(group);

        facultyDao.removeGroup(oldFacultyId, group);
        facultyDao.addGroup(faculty.getId(), group);

        return group;
    }

    public void dismantleGroup(Faculty faculty, Group group) {
        faculty.getGroups().remove(group);

        facultyDao.removeGroup(faculty.getId(), group);
    }

    public Faculty findFacultyById(long id) {
        return facultyDao.findById(id);
    }

    public long findFacultyByGroup(Group group) {
        return facultyDao.findByGroupId(group.getId());
    }

    public StudentCard addStudent(Group group, StudentCard student) {
        return GroupDomain.addStudent(group, student);
    }

    public StudentCard changeStudentGroup(StudentCard student, Group group) {
        return GroupDomain.changeStudentGroup(group, student);
    }

    public TeacherCard hireTeacher(Faculty faculty, TeacherCard teacher, Subject subject){
        TeacherCard newTeacher = TeacherDomain.createTeacher(teacher);

        faculty.getTeachers().add(newTeacher);

        TeacherDomain.addSubject(newTeacher, subject);

        facultyDao.addTeacher(faculty.getId(), newTeacher);

        return newTeacher;
    }

    public void fireTeacher(Faculty faculty, TeacherCard teacher) {
        faculty.getTeachers().remove(teacher);
        TeacherDomain.dismissTeacher(teacher);
    }

    public Auditorium addAuditorium(Faculty faculty, Auditorium auditorium){
        Auditorium newAuditorium = AuditoriumDomain.createAuditorium(auditorium);

        faculty.getAuditoria().add(newAuditorium);

        facultyDao.addAuditorium(faculty.getId(), newAuditorium);

        return newAuditorium;
    }

    public void removeAuditorium(Faculty faculty, Auditorium auditorium) {
        faculty.getAuditoria().remove(faculty);

        AuditoriumDomain.removeAuditorium(auditorium);
    }

    public Subject addSubject(Faculty faculty, Subject subject){
        Subject newSubject = SubjectDomain.createSubject(subject);

        faculty.getSubjects().add(newSubject);

        facultyDao.addSubject(faculty.getId(), newSubject);

        return newSubject;
    }

    public void removeSubject(Faculty faculty, Subject subject){
        faculty.getSubjects().remove(subject);

        SubjectDomain.removeSubject(subject);
    }

    public List<DaySchedule> createSchedule(Faculty faculty) {
        List<DaySchedule> newDaySchedules = DayScheduleDomain.findAllDaySchedules(faculty);

        faculty.getSchedule().setDaySchedules(newDaySchedules);

        return newDaySchedules;
    }
}
