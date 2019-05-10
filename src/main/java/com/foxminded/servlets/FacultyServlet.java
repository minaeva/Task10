package com.foxminded.servlets;

import com.foxminded.domain.FacultyDomain;
import com.foxminded.model.Faculty;
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
        Faculty faculty = facultyDomain.findFacultyById(id);
        if (faculty == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            faculty = facultyDomain.findFacultyByIdFull(id);
            req.setAttribute("faculty", faculty);
            req.getRequestDispatcher("faculty.jsp").forward(req, resp);
        }
    }
}
