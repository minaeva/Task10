package com.foxminded.servlets;

import com.foxminded.domain.AuditoriumDomain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auditoria")
public class AuditoriaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AuditoriumDomain auditoriumDomain = new AuditoriumDomain();
        req.setAttribute("auditoria", auditoriumDomain.findAll());
        req.getRequestDispatcher("auditoria.jsp").forward(req, resp);
    }
}
