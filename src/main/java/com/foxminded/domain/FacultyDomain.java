package com.foxminded.domain;

import com.foxminded.dao.*;
import com.foxminded.dao.impl.*;
import com.foxminded.model.*;
import java.util.List;

public class FacultyDomain {

    private FacultyDao facultyDao = new FacultyDaoImpl();
    private AuditoriumDao auditoriumDao = new AuditoriumDaoImpl();
    private GroupDao groupDao = new GroupDaoImpl();
    private SubjectDao subjectDao = new SubjectDaoImpl();
    private TeacherDao teacherDao = new TeacherDaoImpl();

    public Faculty createFaculty(Faculty faculty) {
        return facultyDao.create(faculty);
    }

    public Faculty findFacultyById(long id) {
        return facultyDao.findById(id);
    }

    public List<Faculty> findAll() {
        return facultyDao.findAll();
    }
    public Faculty findFacultyByIdFull(long id) {
        Faculty faculty = facultyDao.findById(id);
        faculty.setAuditoria(auditoriumDao.findAuditoriaByFacultyId(faculty.getId()));
        faculty.setGroups(groupDao.findGroupsByFacultyId(faculty.getId()));
        faculty.setSubjects(subjectDao.findSubjectsByFacultyId(faculty.getId()));
        faculty.setTeachers(teacherDao.findTeachersByFacultyId(faculty.getId()));
        return faculty;
    }

    public long findFacultyByGroup(Group group) {
        return facultyDao.findByGroupId(group.getId());
    }

    public void removeFaculty(Faculty faculty) {
        facultyDao.delete(faculty);
    }

    public Faculty hireTeacher(Faculty faculty, TeacherCard teacher){
        faculty.getTeachers().add(teacher);
        return facultyDao.addTeacher(faculty.getId(), teacher);
    }

    public void fireTeacher(Faculty faculty, TeacherCard teacher) {
        facultyDao.removeTeacher(faculty.getId(), teacher);
        faculty.getTeachers().remove(teacher);
    }

    public Faculty addAuditorium(Faculty faculty, Auditorium auditorium){
        faculty.getAuditoria().add(auditorium);
        return facultyDao.addAuditorium(faculty.getId(), auditorium);
    }

    public void removeAuditorium(Faculty faculty, Auditorium auditorium) {
        facultyDao.removeAuditorium(faculty.getId(), auditorium);
        faculty.getAuditoria().remove(faculty);
    }

    public Faculty addSubject(Faculty faculty, Subject subject){
        faculty.getSubjects().add(subject);
        return facultyDao.addSubject(faculty.getId(), subject);
    }

    public void removeSubject(Faculty faculty, Subject subject){
        facultyDao.removeSubject(faculty.getId(), subject);
        faculty.getSubjects().remove(subject);
    }

    public Faculty addGroup(Faculty faculty, Group group) {
        faculty.getGroups().add(group);
        return facultyDao.addGroup(group.getId(), group);
    }

    public void dismantleGroup(Faculty faculty, Group group) {
        facultyDao.removeGroup(faculty.getId(), group);
        faculty.getGroups().remove(group);
    }
}
