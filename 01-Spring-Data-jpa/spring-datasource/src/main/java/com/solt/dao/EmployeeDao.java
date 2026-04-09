package com.solt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import com.solt.ds.Employee;

@Component
public class EmployeeDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Employee mapToEmployee(ResultSet rs, int i) {
		try {
			return new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("email"), rs.getString("phone_number"), rs.getDate("hired_date"),
					rs.getDouble("salary"));

		} catch (Exception e) {

		}
		return null;

	}

	public List<Employee> listAllEmployee(){
		return jdbcTemplate.query("""
				select * from employee
				""", this:: mapToEmployee);
	}

	public double getAverageRollCallbackHandaler() {
		AverageRollCallbackHandler handler = new AverageRollCallbackHandler();
		jdbcTemplate.query("""
				select salary from employee
				""", handler);
		return handler.getAverage();
	}
	
	public double getAverageResultSetExtractor() {
		AverageResultSetExtractor extractor = new AverageResultSetExtractor();
		
		return jdbcTemplate.query("""
				select salary from employee
				""", extractor);
				
	}
	
	public List<String> listEmployeeEmail() {
		return jdbcTemplate.queryForList("select email from employee", String.class);
	}
	
	class AverageRollCallbackHandler implements RowCallbackHandler{
		
		private int count;
		private double sum;

		@Override
		public void processRow(ResultSet rs) throws SQLException {
			sum += rs.getDouble("salary");
			count++;
			
		}
		
		public double getAverage() {
			return sum / (double) count;
		}
		
	}
	
	class AverageResultSetExtractor implements ResultSetExtractor<Double>{

		@Override
		public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
			int count = 0;
			double total = 0;
			while (rs.next()) {
				total +=  rs.getDouble("salary");
				count++;
			}
			return total / (double) count;
		}
		
	}
	
	public double getAverageNativeWay() {
		return jdbcTemplate.queryForObject("""
				select avg(salary) from employee
				""", Double.class);
	}
	
	public double getAverageMordenWay() {
		return jdbcTemplate.queryForList("""
				select avg(salary) from employee 
				""", Double.class)
				.stream() // Stream<Double> 
				.mapToDouble(Double::doubleValue) // DoubleStream
				.average()
				.getAsDouble();
	}
	
	public Employee findEmployeeByEmail(String email) {
		return jdbcTemplate.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement("""
						select * from employee where email = ?
						""");
			}
		}, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, email);
				
			}
		}, new ResultSetExtractor<Employee>() {

			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				while (rs.next()) {
					return new Employee(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"),
					rs.getString("email"), rs.getString("phone_number"), rs.getDate("hired_date"),
					rs.getDouble("salary"));
					
				};
				return null;
			}
		});
	}
	
	public Employee findEmployeeById(int id) {
		return jdbcTemplate.queryForObject(""" 
				select * from employee where id = ?
				""",this:: mapToEmployee,id);
	}
	
	public Employee findEmployeeByEmailV2(String email) {
		return jdbcTemplate.queryForObject(""" 
				select * from employee where email = ?
				""", this::mapToEmployee,new Object[] {email});
	}
	
	  public Employee findEmployeeByFirstNameAndLastName(String firstName,
		      String lastName) {
		    return jdbcTemplate.queryForObject("""
		        select * from employee where first_name = ? and last_name = ?
		        """,this::mapToEmployee,new Object[] {firstName,lastName});
	}
	  
	public Employee hightSalaryEmployee() {
		return jdbcTemplate.queryForObject("""
				select * from employee order by salary desc limit 1
				""", this:: mapToEmployee);
	}
	
	public Employee hightSalaryEmployeeV2() {
		return jdbcTemplate.queryForObject("""
				select * from employee where salary=(select max(salary) from employee)
				""", this::mapToEmployee);
	}
	
	public void createEmployee(Employee employee) {
		jdbcTemplate.update("""
				insert into employee(first_name,last_name,email,phone_number,hired_date,salary)
values(?,?,?,?,?,?)
				""",
				employee.firstName(),
				employee.lastName(),
				employee.email(),
				employee.phoneNumber(),
				employee.hiredDate(),
				employee.salary());
	}
	
	public void changeSalaryEmployee(int id,double salary) {
		jdbcTemplate.update("""
				update employee set salary = ? where id = ?
				""", salary, id);
	}
	
	public void deleteById(int id) {
		jdbcTemplate.update("""
				delete from employee where id = ? 
				""", id);
	}

}
