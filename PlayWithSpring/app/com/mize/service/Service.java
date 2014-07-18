package com.mize.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mize.dto.EmployeeDTO;

public interface Service {

	public abstract List<EmployeeDTO> getAllEmployees();

	public abstract EmployeeDTO getEmployeeById(EmployeeDTO employeeDTO) throws EmptyResultDataAccessException;
	
	public abstract int addEmployee(EmployeeDTO employeeDTO)  throws DuplicateKeyException;
	
	public abstract int deleteEmployee(EmployeeDTO employeeDTO);
	
	public abstract int updateEmployee(EmployeeDTO employeeDTO);
}
