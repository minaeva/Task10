package com.foxminded.servlets;

import com.foxminded.dao.DaoException;
import com.foxminded.domain.GroupDomain;
import com.foxminded.domain.StudentDomain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    private StudentDomain studentDomain;
    private GroupDomain groupDomain;

    @Override
    public void init(ServletConfig config) {
        studentDomain = new StudentDomain();
        groupDomain = new GroupDomain();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = 0;

        try {
            id = Integer.valueOf(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            studentDomain.dismissStudentById(id);
        } catch (DaoException e) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
