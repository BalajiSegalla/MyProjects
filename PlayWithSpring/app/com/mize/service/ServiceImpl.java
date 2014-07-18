package com.mize.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.mize.dao.EmployeeDAO;
import com.mize.dto.EmployeeDTO;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{

	@Autowired
	EmployeeDAO employeeDAO;
	
	public List<EmployeeDTO> getAllEmployees(){
		
		return employeeDAO.getAllEmployees();
	}
	
	public EmployeeDTO getEmployeeById(EmployeeDTO employeeDTO) throws EmptyResultDataAccessException {
		
		return employeeDAO.getEmployeeById(employeeDTO);
	}
	
	public int addEmployee(EmployeeDTO employeeDTO) throws DuplicateKeyException {
		
		return employeeDAO.addEmployee(employeeDTO);
	}
	
	public int deleteEmployee(EmployeeDTO employeeDTO){
		
		return employeeDAO.deleteEmployee(employeeDTO);
	}
	
	public int updateEmployee(EmployeeDTO employeeDTO){
		
		return employeeDAO.updateEmployee(employeeDTO);
	}

	
	
}
