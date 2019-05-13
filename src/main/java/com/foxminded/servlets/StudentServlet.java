package com.foxminded.servlets;

import com.foxminded.domain.StudentDomain;
import com.foxminded.model.StudentCard;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeParseException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDomain studentDomain;

     @Override
    public void init(ServletConfig config) {
        studentDomain = new StudentDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String fromDate;
        String toDate;
        int id;

        try{
            id = Integer.valueOf(req.getParameter("id"));
            fromDate = req.getParameter("from");
            if (fromDate == null) {
                fromDate = "";
            }
            toDate = req.getParameter("to");
            if (toDate == null) {
                toDate = "";
            }
            StudentCard student = studentDomain.findStudentById(id);
            if (student == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                req.setAttribute("student", student);
                req.setAttribute("from", fromDate);
                req.setAttribute("to", toDate);
                if (!fromDate.isEmpty() || !toDate.isEmpty()) {
                    req.setAttribute("lessons", studentDomain.findScheduleInPeriod(student, fromDate, toDate));
                }
                req.getRequestDispatcher("student.jsp").forward(req, resp);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
