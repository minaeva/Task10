package com.foxminded;

import com.foxminded.domain.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class UniversityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("q") == null) {
            req.getRequestDispatcher("university.jsp").forward(req, resp);
        }
        else {
            if (!doRouting(req).equals("")) {
                req.getRequestDispatcher(doRouting(req) + ".jsp").forward(req, resp);

            }
        }
    }

    private String doRouting(HttpServletRequest req) {
        String page = "";
        if ((req.getParameter("no") != null) && (req.getParameter("no").equals("all"))) {
            switch (req.getParameter("q")) {
                case "faculty":
                    FacultyDomain facultyDomain = new FacultyDomain();
                    req.setAttribute("faculties", facultyDomain.findAll());
                    return "faculties";
                case  "group":
                    GroupDomain groupDomain = new GroupDomain();
                    req.setAttribute("groups", groupDomain.findAll());
                    return "groups";
                case "student":
                    StudentDomain studentDomain = new StudentDomain();
                    req.setAttribute("students", studentDomain.findAll());
                    return "students";
                case "teacher":
                    TeacherDomain teacherDomain = new TeacherDomain();
                    req.setAttribute("teachers", teacherDomain.findAll());
                    return "teachers";
                case "subject":
                    SubjectDomain subjectDomain = new SubjectDomain();
                    req.setAttribute("subjects", subjectDomain.findAll());
                    return "subjects";
            }
        }
        else {
            int id = Integer.valueOf(req.getParameter("no"));
            switch (req.getParameter("q")) {
                case "faculty":
                    FacultyDomain facultyDomain = new FacultyDomain();
                    req.setAttribute("faculty", facultyDomain.findFacultyById(id));
                    GroupDomain groupDomain = new GroupDomain();
                    req.setAttribute("groups", groupDomain.findGroupsByFaculty(id));
                    return "faculty";
                case  "group":
                    groupDomain = new GroupDomain();
                    req.setAttribute("group", groupDomain.findGroupById(id));
                    StudentDomain studentDomain = new StudentDomain();
                    req.setAttribute("students", studentDomain.findStudentsByGroup(id));
                    return "group";
                case "student":
                    studentDomain = new StudentDomain();
                    req.setAttribute("student", studentDomain.findStudentById(id));
                    return "student";
                case "teacher":
                    TeacherDomain teacherDomain = new TeacherDomain();
                    req.setAttribute("teacher", teacherDomain.findTeacherById(id));
                    return "teacher";
                case "subject":
                    SubjectDomain subjectDomain = new SubjectDomain();
                    req.setAttribute("subjects", subjectDomain.findSubjectById(id));
                    return "subject";
            }
        }
        return "";
    }
}
