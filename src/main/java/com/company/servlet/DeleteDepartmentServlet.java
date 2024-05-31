package com.company.servlet;

import com.company.dao.impl.DepartmentDAO;
import com.company.dao.inter.IDepartmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteDepartment")
public class DeleteDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentIdStr = req.getParameter("departmentId");

        if (departmentIdStr == null || departmentIdStr.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/department");
            return;
        }
        Long departmentId = Long.parseLong(departmentIdStr);

        try {
            IDepartmentDAO departmentDAO = new DepartmentDAO();
            departmentDAO.deleteDepartment(departmentId);

            resp.sendRedirect(req.getContextPath() + "/department");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}

