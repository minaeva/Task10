package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createStudent")
public class CreateStudentServlet extends HttpServlet {

    private GroupDomain groupDomain;

    @Override
    public void init(ServletConfig config) {
        groupDomain = new GroupDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("groups", groupDomain.findAll());
        req.getRequestDispatcher("editStudent.jsp").forward(req, resp);
    }
}
