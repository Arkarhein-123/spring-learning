package spel.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBean2 {
	@Value("Life is Beautiful")
	public String msg;
	
}
