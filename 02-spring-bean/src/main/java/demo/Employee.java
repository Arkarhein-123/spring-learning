package demo;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	private String firstName = "Orion";
	private String lastName = "Justin";
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
}
