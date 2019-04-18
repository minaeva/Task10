package com.foxminded;

import com.foxminded.domain.StudentDomain;
import com.foxminded.model.StudentCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class UniversityServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StudentDomain studentDomain = new StudentDomain();
        long groupNumber = 1;

        List<StudentCard> students = studentDomain.findStudentsByGroup(groupNumber);

        req.setAttribute("groupNumber", groupNumber);
        req.setAttribute("students", students);
        req.getRequestDispatcher("group.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        super.doPost(req, resp);
    }

}