package com.foxminded.servlets;

import com.foxminded.dao.DaoException;
import com.foxminded.dao.DomainException;
import com.foxminded.domain.GroupDomain;
import com.foxminded.domain.StudentDomain;
import com.foxminded.model.Group;
import com.foxminded.model.StudentCard;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

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
        int id = Integer.valueOf(req.getParameter("id"));
        LocalDate fromDate = stringToLocalDate(req.getParameter("from"));
        LocalDate toDate = stringToLocalDate(req.getParameter("to"));

        StudentCard student = studentDomain.findStudentById(id);

        req.setAttribute("student", student);
        if (req.getParameterMap().containsKey("schedule")) {
            req.setAttribute("lessons", studentDomain.findScheduleInPeriod(student, fromDate, toDate));
        }

        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        int groupId = Integer.valueOf(req.getParameter("group"));

        StudentCard student = studentDomain.findStudentById(id);
        Group group = groupDomain.findGroupById(groupId);
        student.setName(req.getParameter("name"));
        studentDomain.updateStudent(student);
        groupDomain.addStudent(student, group);

        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
