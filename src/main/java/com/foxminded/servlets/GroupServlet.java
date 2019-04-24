package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import com.foxminded.model.Group;

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
        Group group = groupDomain.findGroupById(id);
        if (group == null) {
            req.getRequestDispatcher("error-404.jsp").forward(req, resp);
        } else {
            group = groupDomain.findGroupByIdFull(id);
            req.setAttribute("group", group);
            req.getRequestDispatcher("group.jsp").forward(req, resp);
        }
    }
}
