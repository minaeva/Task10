package com.foxminded.servlets;

import com.foxminded.dao.DaoException;
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
        int groupId = 0;
        Group group = null;

        try {
            groupId = Integer.valueOf(req.getParameter("group"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        StudentCard student = new StudentCard(req.getParameter("name"));
        try {
            group = groupDomain.findGroupById(groupId);
        } catch (DaoException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        try {
            studentDomain.createStudent(student);
            groupDomain.addStudent(student, group);
        } catch (DaoException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        doGet(req, resp);
    }
}
