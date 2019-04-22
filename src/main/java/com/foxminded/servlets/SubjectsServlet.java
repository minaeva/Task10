package com.foxminded.servlets;

import com.foxminded.domain.SubjectDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subjects")
public class SubjectsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        SubjectDomain subjectDomain= new SubjectDomain();
        req.setAttribute("subjects", subjectDomain.findAll());
        req.getRequestDispatcher("subjects.jsp").forward(req, resp);
    }
}
