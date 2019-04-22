package com.foxminded.servlets;

import com.foxminded.domain.TeacherDomain;
import com.foxminded.model.TeacherCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/teacher")
public class TeacherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        TeacherDomain teacherDomain = new TeacherDomain();
        TeacherCard teacher = teacherDomain.findTeacherById(id);
        req.setAttribute("teacher", teacher);
        req.setAttribute("lessons", teacherDomain.createSchedule(teacher));
        req.getRequestDispatcher("teacher.jsp").forward(req, resp);
    }
}
