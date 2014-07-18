package com.mize.dao;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mize.dto.EmployeeDTO;

@Component
public class EmployeeDAOImpl implements EmployeeDAO{

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void setDataSource(DataSource datasource) {
		this.dataSource = datasource;	
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<EmployeeDTO> getAllEmployees(){
		
		String getAllEmployees = "select * from balaji_employee1";
		@SuppressWarnings("rawtypes")
		List<EmployeeDTO> employees  = jdbcTemplate.query(getAllEmployees,
				new BeanPropertyRowMapper(EmployeeDTO.class));
	 return employees;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public EmployeeDTO getEmployeeById(EmployeeDTO employeeDTO) throws EmptyResultDataAccessException
	{
		int id = employeeDTO.getId();
		String getEmployeeByIdQuery = "select * from balaji_employee1 where id=?";
		EmployeeDTO employee = (EmployeeDTO)jdbcTemplate.queryForObject(getEmployeeByIdQuery, new Object[]{id},new BeanPropertyRowMapper(EmployeeDTO.class));
		System.out.println(employee);
		return employee;
	}
	
	public int addEmployee(EmployeeDTO employee) throws DuplicateKeyException
	{
				
		String sql ="insert into balaji_employee1 values(?,?,?,?)";
		
		int rows=jdbcTemplate.update(sql, new Object[]{
				employee.getId(),employee.getName(),employee.getDepartment(),employee.getSalary()});
		return rows;
	}
	
	public int deleteEmployee(EmployeeDTO employeeDTO)
	{
		String deleteQuery = "delete from balaji_employee1 where id=?";
		int rows = jdbcTemplate.update(deleteQuery, new Object[]{employeeDTO.getId()});
		return rows;
				
	}
	
	public int updateEmployee(EmployeeDTO employee)
	{
		String updateQuery = "update balaji_employee set name=?,department=?,salary=? where id=?";
		int rows = jdbcTemplate.update(updateQuery, new Object[]{employee.getName(),employee.getDepartment(),employee.getSalary(),employee.getId()});
		return rows;
	}


}
