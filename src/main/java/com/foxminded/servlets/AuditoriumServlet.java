package com.foxminded.servlets;

import com.foxminded.domain.AuditoriumDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auditorium")
public class AuditoriumServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("id"));
        AuditoriumDomain auditoriumDomain = new AuditoriumDomain();
        req.setAttribute("auditorium", auditoriumDomain.findAuditoriumById(id));
        req.getRequestDispatcher("auditorium.jsp").forward(req, resp);
    }
}
