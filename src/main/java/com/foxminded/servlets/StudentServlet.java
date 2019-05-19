package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import com.foxminded.domain.StudentDomain;
import com.foxminded.model.Group;
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
import static com.foxminded.util.ParseDate.stringToLocalDate;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDomain studentDomain;
    private GroupDomain groupDomain;

    @Override
    public void init(ServletConfig config) {
        studentDomain = new StudentDomain();
        groupDomain = new GroupDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = 0;
        LocalDate fromDate = null;
        LocalDate toDate = null;

        try {
            id = Integer.valueOf(req.getParameter("id"));
            fromDate = stringToLocalDate(req.getParameter("from"));
            toDate = stringToLocalDate(req.getParameter("to"));
        } catch (DateTimeParseException | NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        StudentCard student = studentDomain.findStudentById(id);
        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("student", student);
        if (req.getParameterMap().containsKey("schedule")) {
            req.setAttribute("lessons", studentDomain.findScheduleInPeriod(student, fromDate, toDate));
        }
        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = 0;
        int groupId = 0;
        try {
            id = Integer.valueOf(req.getParameter("id"));
            groupId = Integer.valueOf(req.getParameter("group"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        StudentCard student = studentDomain.findStudentById(id);
        if (student == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Group newGroup = groupDomain.findGroupByIdFull(groupId);
        if (newGroup == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        groupDomain.moveStudentToGroup(student, newGroup);
        student.setName(req.getParameter("name"));
        studentDomain.updateStudent(student);

        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
