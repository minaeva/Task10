package com.foxminded.servlets;

import com.foxminded.domain.StudentDomain;
import com.foxminded.model.StudentCard;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDomain studentDomain;

    @Override
    public void init(ServletConfig config) {
        studentDomain = new StudentDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        StudentCard student = studentDomain.findStudentById(id);
        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            req.setAttribute("student", student);
            req.setAttribute("lessons", studentDomain.findSchedule(student));
            req.getRequestDispatcher("student.jsp").forward(req, resp);
        }
    }
}
