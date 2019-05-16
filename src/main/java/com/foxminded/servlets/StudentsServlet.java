package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import com.foxminded.domain.StudentDomain;
import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

    private StudentDomain studentDomain;
    private GroupDomain groupDomain;

    @Override
    public void init(ServletConfig config) {
        studentDomain = new StudentDomain();
        groupDomain = new GroupDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("students", studentDomain.findAll());
        req.getRequestDispatcher("students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getParameter("action").equals("create")) {
            createNew(req, resp);
        } else if (req.getParameter("action").equals("delete")) {
            delete(req, resp);
        } else if (req.getParameter("action").equals("update")) {
            update(req, resp);
        }
        doGet(req, resp);
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        StudentCard student = studentDomain.findStudentById(id);
        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Group oldGroup = groupDomain.findGroupByStudentFull(student);
        groupDomain.removeStudent(oldGroup, student);
        studentDomain.dismissStudent(student);
    }

    private void createNew(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int groupId = 0;
        try {
            groupId = Integer.valueOf(req.getParameter("group"));

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        StudentCard newStudent = new StudentCard(req.getParameter("name"));
        req.setAttribute("student", studentDomain.createStudent(newStudent));
        Group newGroup = groupDomain.findGroupByIdFull(groupId);
        groupDomain.addStudent(newGroup, newStudent);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = 0;
        int groupId = 0;
        try {
            id = Integer.valueOf(req.getParameter("id"));
            groupId = Integer.valueOf(req.getParameter("group"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        StudentCard student = studentDomain.findStudentById(id);
        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Group newGroup = groupDomain.findGroupByIdFull(groupId);
        if (newGroup == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Group oldGroup = groupDomain.findGroupByStudentFull(student);
        groupDomain.removeStudent(oldGroup, student);
        groupDomain.addStudent(newGroup, student);
        student.setName(req.getParameter("name"));
        studentDomain.updateStudent(student);
    }
}
