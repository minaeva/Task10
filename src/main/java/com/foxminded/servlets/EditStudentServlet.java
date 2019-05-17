package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import com.foxminded.domain.StudentDomain;
import com.foxminded.model.StudentCard;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {

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

        req.setAttribute("student", student);
        req.setAttribute("studentGroup", groupDomain.findGroupByStudent(student));

        req.setAttribute("groups", groupDomain.findAll());
        req.getRequestDispatcher("editStudent.jsp").forward(req, resp);
    }
}
