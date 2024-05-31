package com.company.servlet;

import com.company.config.FreeMarkerConfig;
import com.company.dao.impl.DepartmentDAO;
import com.company.dao.inter.IDepartmentDAO;
import com.company.model.Department;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/department")
public class DepartmentServlet extends HttpServlet {
    private Configuration cfg;

    @Override
    public void init() {
        cfg = FreeMarkerConfig.getConfiguration();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IDepartmentDAO departmentDAO = new DepartmentDAO();
        try {
            List<Department> departments = departmentDAO.getAllDepartments();
            Map<String, Object> templateData = new HashMap<>();
            templateData.put("departments", departments);
            templateData.put("contextPath", req.getContextPath());

            Template template = cfg.getTemplate("department.ftl");
            resp.setContentType("text/html; charset=UTF-8");
            try (PrintWriter writer = resp.getWriter()) {
                template.process(templateData, writer);
            } catch (TemplateException e) {
                throw new ServletException("Template error", e);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
