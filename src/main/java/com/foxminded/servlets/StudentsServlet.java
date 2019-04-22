package com.foxminded.servlets;

import com.foxminded.domain.StudentDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students")
public class StudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StudentDomain studentDomain = new StudentDomain();
        req.setAttribute("students", studentDomain.findAll());
        req.getRequestDispatcher("students.jsp").forward(req, resp);
    }
}
