package com.company.dao.impl;


import com.company.dao.inter.IEmployeeDAO;
import com.company.dto.EmployeeResponseDTO;
import com.company.dto.EmployeeRequestDTO;
import com.company.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {

    @Override
    public List<EmployeeResponseDTO> getAllEmployee() throws SQLException {
        List<EmployeeResponseDTO> employees = new ArrayList<>();
        String query = "SELECT e.employee_id, e.employee_name, d.department_name " +
                "FROM employee e " +
                "JOIN department d ON e.fk_department_id = d.department_id where e.action='A' and d.action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO();
                employeeResponseDTO.setEmployeeId(resultSet.getLong("employee_id"));
                employeeResponseDTO.setEmployeeName(resultSet.getString("employee_name"));
                employeeResponseDTO.setDepartmentName(resultSet.getString("department_name"));
                employees.add(employeeResponseDTO);
            }
        }
        return employees;
    }

    @Override
    public void addEmployee(EmployeeRequestDTO employeeRequestDTO) throws SQLException {
        String query = "INSERT INTO employee(employee_name, fk_department_id,action) VALUES (?,?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, employeeRequestDTO.getEmployeeName());
            preparedStatement.setLong(2, employeeRequestDTO.getDepartmentId());
            preparedStatement.setString(3, "A");
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) throws SQLException {
        String query = "UPDATE employee SET action = 'D' WHERE employee_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long employeeId) throws SQLException {
        EmployeeResponseDTO employee = null;
        String query = "SELECT e.employee_id, e.employee_name, d.department_name FROM employee e " +
                "LEFT JOIN department d ON e.fk_department_id = d.department_id WHERE e.employee_id = ? and e.action='A' and d.action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new EmployeeResponseDTO();
                    employee.setEmployeeId(resultSet.getLong("employee_id"));
                    employee.setEmployeeName(resultSet.getString("employee_name"));
                    employee.setDepartmentName(resultSet.getString("department_name"));
                }
            }
        }
        return employee;
    }

    @Override
    public void updateEmployee(Long employeeId, String employeeName, Long departmentId) throws SQLException {
        String updateQuery = "UPDATE employee SET employee_name = ?, fk_department_id = ? WHERE employee_id = ? and action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, employeeName);
            preparedStatement.setLong(2, departmentId);
            preparedStatement.setLong(3, employeeId);
            preparedStatement.executeUpdate();
        }
    }


}

