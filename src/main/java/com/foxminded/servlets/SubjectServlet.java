package com.foxminded.servlets;

import com.foxminded.domain.SubjectDomain;
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
        req.setAttribute("subject", subjectDomain.findSubjectById(id));
        req.getRequestDispatcher("subject.jsp").forward(req, resp);
    }
}
