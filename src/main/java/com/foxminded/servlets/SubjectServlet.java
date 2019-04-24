package com.foxminded.servlets;

import com.foxminded.domain.SubjectDomain;
import com.foxminded.model.Subject;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/subject")
public class SubjectServlet extends HttpServlet {

    private SubjectDomain subjectDomain;

    @Override
    public void init(ServletConfig config) {
        subjectDomain = new SubjectDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Subject subject = subjectDomain.findSubjectById(id);
        if (subject == null) {
            req.getRequestDispatcher("error-404.jsp").forward(req, resp);
        } else {
            req.setAttribute("subject", subject);
            req.getRequestDispatcher("subject.jsp").forward(req, resp);
        }
    }
}
