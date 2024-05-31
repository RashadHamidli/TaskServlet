package com.company.dao.inter;

import com.company.model.Department;
import com.company.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IDepartmentDAO {

    Department getDepartmentByName(String departmentName) throws SQLException;

    List<Department> getAllDepartments() throws SQLException;

    Department getDepartmentById(Long departmentId) throws SQLException;

    void addDepartment(Department department) throws SQLException;

    void updateDepartment(Department department) throws SQLException;

    void deleteDepartment(Long departmentId) throws SQLException;
}
