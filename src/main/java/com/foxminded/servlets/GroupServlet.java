package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/group")
public class GroupServlet extends HttpServlet {

    private GroupDomain groupDomain;

    @Override
    public void init(ServletConfig config) {
        groupDomain = new GroupDomain();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        req.setAttribute("group", groupDomain.findGroupById(id));
        req.setAttribute("students", groupDomain.findGroupByIdFull(id).getStudents());
        req.getRequestDispatcher("group.jsp").forward(req, resp);
    }
}
