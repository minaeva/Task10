package com.foxminded.servlets;

import com.foxminded.domain.GroupDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/groups")
public class GroupsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        GroupDomain groupDomain = new GroupDomain();
        req.setAttribute("groups", groupDomain.findAll());
        req.getRequestDispatcher("groups.jsp").forward(req, resp);
    }
}
