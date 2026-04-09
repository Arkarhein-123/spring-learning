package spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;


public class MainSpringSpelParser {
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		System.out.println(parser.parseExpression("'Hello'.concat(' world')").getValue());
		System.out.println(parser.parseExpression("24*45").getValue());
		System.out.println(parser.parseExpression("5 < 10").getValue());
		
	}
}
