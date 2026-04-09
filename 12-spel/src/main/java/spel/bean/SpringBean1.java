package spel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component	
public class SpringBean1 {
	@Value("#{'${app.name}'.toUpperCase()}")
	private String name;
	
	@Value("#{100 * 10}")
	private int incomeTax;
	@Value("#{springBean2.msg}")
	private String sth;
	public void print() {
		System.out.println("Name : "+name +
				"\nTax : " + incomeTax +
				"\nMessage : "+sth);
	}
}
