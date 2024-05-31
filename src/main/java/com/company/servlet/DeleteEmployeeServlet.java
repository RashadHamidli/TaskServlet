package com.company.servlet;

import com.company.dao.impl.EmployeeDAO;
import com.company.dao.inter.IEmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeId = req.getParameter("employeeId");

        try {
            IEmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.deleteEmployee(Long.parseLong(employeeId));
            resp.sendRedirect(req.getContextPath() + "/employee");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}

