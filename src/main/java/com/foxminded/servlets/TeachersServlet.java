package com.foxminded.servlets;

import com.foxminded.domain.TeacherDomain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {

    private TeacherDomain teacherDomain;

    @Override
    public void init(ServletConfig config) {
        teacherDomain = new TeacherDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("teachers", teacherDomain.findAll());
        req.getRequestDispatcher("teachers.jsp").forward(req, resp);
    }
}
