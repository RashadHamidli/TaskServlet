package com.company;

import com.company.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Launcher {
    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.getConnector();

        String webappDirLocation = "src/main/webapp/";
        Context context = tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        Tomcat.addServlet(context, "employeeServlet", new EmployeeServlet());
        Tomcat.addServlet(context, "deleteEmployeeServlet", new DeleteEmployeeServlet());
        Tomcat.addServlet(context, "updateEmployeeServlet", new UpdateEmployeeServlet());
        Tomcat.addServlet(context,"addEmployeeServlet", new AddEmployeeServlet());
        Tomcat.addServlet(context,"departmentServlet", new DepartmentServlet());
        Tomcat.addServlet(context,"updateDepartmentServlet", new UpdateDepartmentServlet());
        Tomcat.addServlet(context,"deleteDepartmentServlet", new DeleteDepartmentServlet());
        Tomcat.addServlet(context,"addDepartmentServlet", new AddDepartmentServlet());

        context.addServletMappingDecoded("/employee", "employeeServlet");
        context.addServletMappingDecoded("/deleteEmployee", "deleteEmployeeServlet");
        context.addServletMappingDecoded("/updateEmployee", "updateEmployeeServlet");
        context.addServletMappingDecoded("/addEmployee", "addEmployeeServlet");
        context.addServletMappingDecoded("/department", "departmentServlet");
        context.addServletMappingDecoded("/updateDepartment", "updateDepartmentServlet");
        context.addServletMappingDecoded("/deleteDepartment", "deleteDepartmentServlet");
        context.addServletMappingDecoded("/addDepartment", "addDepartmentServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
