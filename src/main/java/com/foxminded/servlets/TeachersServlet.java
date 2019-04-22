package com.foxminded.servlets;

import com.foxminded.domain.TeacherDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teachers")
public class TeachersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        TeacherDomain teacherDomain = new TeacherDomain();
        req.setAttribute("teachers", teacherDomain.findAll());
        req.getRequestDispatcher("teachers.jsp").forward(req, resp);
    }
}
