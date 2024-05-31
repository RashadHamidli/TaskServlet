package com.company.dao.inter;


import com.company.dto.EmployeeRequestDTO;
import com.company.dto.EmployeeResponseDTO;

import java.sql.SQLException;
import java.util.List;

public interface IEmployeeDAO {

    List<EmployeeResponseDTO> getAllEmployee() throws SQLException;

    void addEmployee(EmployeeRequestDTO employeeRequestDTO) throws SQLException;

    EmployeeResponseDTO getEmployeeById(Long employeeId) throws SQLException;

    void deleteEmployee(Long employeeId) throws SQLException;

    void updateEmployee(Long employeeId, String employeeName, Long departmentId) throws SQLException;


}

