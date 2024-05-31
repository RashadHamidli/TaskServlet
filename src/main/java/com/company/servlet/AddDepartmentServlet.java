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
import java.util.Map;

@WebServlet("/addDepartment")
public class AddDepartmentServlet extends HttpServlet {
    private Configuration cfg;

    @Override
    public void init() {
        cfg = FreeMarkerConfig.getConfiguration();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("contextPath", req.getContextPath());

        Template template = cfg.getTemplate("addDepartment.ftl");
        resp.setContentType("text/html; charset=UTF-8");
        try (PrintWriter writer = resp.getWriter()) {
            template.process(templateData, writer);
        } catch (TemplateException e) {
            throw new ServletException("Template error", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String departmentName = req.getParameter("departmentName");

        try {
            IDepartmentDAO departmentDAO = new DepartmentDAO();
            Department department = new Department();
            department.setDepartmentName(departmentName);

            departmentDAO.addDepartment(department);

            resp.sendRedirect(req.getContextPath() + "/department");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}

