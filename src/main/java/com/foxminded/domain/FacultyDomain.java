package com.foxminded.domain;

import com.foxminded.dao.*;
import com.foxminded.dao.impl.*;
import com.foxminded.model.*;

public class FacultyDomain {

    private FacultyDao facultyDao = new FacultyDaoImpl();

    public Group addGroup(Faculty faculty, Group group) {
        Group newGroup = GroupDomain.createGroup(group);

        faculty.getGroups().add(newGroup);

        return facultyDao.addGroup(group.getId(), newGroup);
    }

    public Group updateGroup(Faculty faculty, Group group) {
        long oldFacultyId = findFacultyByGroup(group);
        Faculty oldFaculty = findFacultyById(oldFacultyId);

        oldFaculty.getGroups().remove(group);
        faculty.getGroups().add(group);

        facultyDao.removeGroup(oldFacultyId, group);
        return facultyDao.addGroup(faculty.getId(), group);
    }

    public boolean dismantleGroup(Faculty faculty, Group group) {
        faculty.getGroups().remove(group);

        try {
            facultyDao.removeGroup(faculty.getId(), group);
        } catch (DaoException e) {
            return false;
        }
        return true;
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

/*    public List<DaySchedule> createSchedule(long facultyId) {
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

 */
}
