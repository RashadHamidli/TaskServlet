package com.company.servlet;

import com.company.config.FreeMarkerConfig;
import com.company.dao.impl.DepartmentDAO;
import com.company.dao.impl.EmployeeDAO;
import com.company.dao.inter.IDepartmentDAO;
import com.company.dao.inter.IEmployeeDAO;
import com.company.dto.EmployeeResponseDTO;
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

@WebServlet("/updateEmployee")
public class UpdateEmployeeServlet extends HttpServlet {

    private Configuration cfg;

    @Override
    public void init() {
        cfg = FreeMarkerConfig.getConfiguration();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeIdStr = req.getParameter("employeeId");
        if (employeeIdStr == null || employeeIdStr.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/employee");
            return;
        }
        Long employeeId = Long.parseLong(employeeIdStr);

        try {
            IEmployeeDAO employeeDAO = new EmployeeDAO();
            IDepartmentDAO departmentDAO = new DepartmentDAO();

            EmployeeResponseDTO employee = employeeDAO.getEmployeeById(employeeId);
            if (employee == null) {
                resp.sendRedirect(req.getContextPath() + "/employee");
                return;
            }

            List<Department> departments = departmentDAO.getAllDepartments();

            Map<String, Object> templateData = new HashMap<>();
            templateData.put("employee", employee);
            templateData.put("departments", departments);
            templateData.put("contextPath", req.getContextPath());

            Template template = cfg.getTemplate("updateEmployee.ftl");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String employeeIdStr = req.getParameter("employeeId");
        String employeeName = req.getParameter("employeeName");
        String departmentIdStr = req.getParameter("departmentId");

        if (employeeIdStr == null || employeeName == null || departmentIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/employee");
            return;
        }

        Long employeeId = Long.parseLong(employeeIdStr);
        Long departmentId = Long.parseLong(departmentIdStr);

        try {
            IEmployeeDAO employeeDAO = new EmployeeDAO();
            employeeDAO.updateEmployee(employeeId, employeeName, departmentId);

            resp.sendRedirect(req.getContextPath() + "/employee");
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}


