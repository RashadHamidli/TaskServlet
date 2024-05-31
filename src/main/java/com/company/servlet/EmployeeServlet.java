package com.company.servlet;

import com.company.config.FreeMarkerConfig;
import com.company.dao.impl.EmployeeDAO;
import com.company.dao.inter.IEmployeeDAO;
import com.company.dto.EmployeeResponseDTO;
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

@WebServlet(name = "employeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {

    private Configuration cfg;

    @Override
    public void init() {
        cfg = FreeMarkerConfig.getConfiguration();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        IEmployeeDAO employeeDAO = new EmployeeDAO();
        List<EmployeeResponseDTO> employees;
        try {
            employees = employeeDAO.getAllEmployee();
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("employees", employees);
        templateData.put("contextPath", req.getContextPath());

        Template template = cfg.getTemplate("employee.ftl");

        try (PrintWriter writer = resp.getWriter()) {
            template.process(templateData, writer);
        } catch (TemplateException e) {
            throw new ServletException("Template error", e);
        }

    }


}
