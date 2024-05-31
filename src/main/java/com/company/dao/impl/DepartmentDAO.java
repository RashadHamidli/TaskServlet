package com.company.dao.impl;

import com.company.dao.inter.IDepartmentDAO;
import com.company.model.Department;
import com.company.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO implements IDepartmentDAO {

    @Override
    public Department getDepartmentByName(String departmentName) throws SQLException {
        Department department = null;
        String query = "SELECT department_id, department_name FROM department WHERE department_name = ? and action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, departmentName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    department = new Department();
                    department.setDepartmentId(resultSet.getLong("department_id"));
                    department.setDepartmentName(resultSet.getString("department_name"));
                }
            }
        }
        return department;
    }

    @Override
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT department_id, department_name FROM department where action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Department department = new Department();
                department.setDepartmentId(resultSet.getLong("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));
                departments.add(department);
            }
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(Long departmentId) throws SQLException {
        Department department = null;
        String query = "SELECT department_id, department_name FROM department WHERE department_id = ? and action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, departmentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    department = new Department();
                    department.setDepartmentId(resultSet.getLong("department_id"));
                    department.setDepartmentName(resultSet.getString("department_name"));
                }
            }
        }
        return department;
    }

    @Override
    public void addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO department (department_name, action) VALUES (?,?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, "A");
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateDepartment(Department department) throws SQLException {
        String query = "UPDATE department SET department_name = ? WHERE department_id = ? and action='A'";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setLong(2, department.getDepartmentId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteDepartment(Long departmentId) throws SQLException {
        String query = "UPDATE department SET action='D' WHERE department_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, departmentId);
            preparedStatement.executeUpdate();
        }
    }
}
