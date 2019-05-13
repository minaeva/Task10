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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import static com.foxminded.util.ParseDate.firstEntry;
import static com.foxminded.util.ParseDate.stringToLocalDate;

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
        try{
            int id = Integer.valueOf(req.getParameter("id"));
            StudentCard student = studentDomain.findStudentById(id);

            if (student == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);

            } else {
                req.setAttribute("student", student);

                if (!firstEntry(req.getParameter("from"), req.getParameter("to"))) {
                    LocalDate fromDate = stringToLocalDate(req.getParameter("from"));
                    LocalDate toDate = stringToLocalDate(req.getParameter("to"));
                    req.setAttribute("lessons", studentDomain.findScheduleInPeriod(student, fromDate, toDate));
                }
                req.getRequestDispatcher("student.jsp").forward(req, resp);
            }
        } catch (DateTimeParseException | NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
