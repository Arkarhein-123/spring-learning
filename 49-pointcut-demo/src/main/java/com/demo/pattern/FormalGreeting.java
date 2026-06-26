package com.demo.pattern;

public class FormalGreeting implements IGreet{

    @Override
    public String greet(String name) {
        return "Hello "+name;
    }
}
