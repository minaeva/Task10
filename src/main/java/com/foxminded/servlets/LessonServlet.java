package com.foxminded.servlets;

import com.foxminded.domain.LessonDomain;
import com.foxminded.model.Lesson;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/lesson")
public class LessonServlet extends HttpServlet {

    private LessonDomain lessonDomain;

    @Override
    public void init(ServletConfig config) {
        lessonDomain = new LessonDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        Lesson lesson = lessonDomain.findLessonById(id);
        req.setAttribute("lesson", lesson);
        req.getRequestDispatcher("lesson.jsp").forward(req, resp);
    }
}
