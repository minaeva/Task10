package com.foxminded.servlets;

import com.foxminded.domain.TeacherDomain;
import com.foxminded.model.TeacherCard;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

    private TeacherDomain teacherDomain;

    @Override
    public void init(ServletConfig config) {
        teacherDomain = new TeacherDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        TeacherCard teacher = teacherDomain.findTeacherById(id);
        if (teacher == null) {
            req.getRequestDispatcher("error-404.jsp").forward(req, resp);
        }
        else {
            req.setAttribute("teacher", teacher);
            req.setAttribute("lessons", teacherDomain.createSchedule(teacher));
            req.getRequestDispatcher("teacher.jsp").forward(req, resp);
        }
    }
}
