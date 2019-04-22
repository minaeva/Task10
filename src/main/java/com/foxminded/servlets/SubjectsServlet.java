package com.foxminded.servlets;

import com.foxminded.domain.SubjectDomain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subjects")
public class SubjectsServlet extends HttpServlet {

    private SubjectDomain subjectDomain;

    @Override
    public void init(ServletConfig config) {
        subjectDomain = new SubjectDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("subjects", subjectDomain.findAll());
        req.getRequestDispatcher("subjects.jsp").forward(req, resp);
    }
}
