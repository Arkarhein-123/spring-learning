package propertysource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean1 {
	@Value("${app.name}")
	private String name;
	@Value("${income.tax}")
	private int tax;
	
	@Value("${JAVA_HOME}")
	private String JAVAHOME;
	
	@Value("${OS}")
	private String OS;
	
	@Value("${Path}")
	private String Path;
	
	public void print() {
		System.out.println("App Name: "+name+"\nTax: "+tax +"\n"
				+ "Path : " + Path +"\n"
				+ "JAVA_HOME : " + JAVAHOME+"\n"
				+ "OS : "+OS);
	}
	
	
}
 