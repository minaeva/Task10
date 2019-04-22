package com.foxminded.servlets;

import com.foxminded.domain.FacultyDomain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    private FacultyDomain facultyDomain;

    @Override
    public void init(ServletConfig config) {
        facultyDomain = new FacultyDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("faculty", facultyDomain.findFacultyByIdFull(id));
        req.getRequestDispatcher("faculty.jsp").forward(req, resp);
    }
}
