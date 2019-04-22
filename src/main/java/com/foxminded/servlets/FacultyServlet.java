package com.foxminded.servlets;

import com.foxminded.domain.FacultyDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        FacultyDomain facultyDomain = new FacultyDomain();
        req.setAttribute("faculty", facultyDomain.findFacultyById(id));
        req.setAttribute("groups", facultyDomain.findFacultyByIdFull(id).getGroups());
        req.setAttribute("teachers", facultyDomain.findFacultyByIdFull(id).getTeachers());
        req.setAttribute("subjects", facultyDomain.findFacultyByIdFull(id).getSubjects());
        req.setAttribute("auditoria", facultyDomain.findFacultyByIdFull(id).getAuditoria());
        req.getRequestDispatcher("faculty.jsp").forward(req, resp);
    }
}
