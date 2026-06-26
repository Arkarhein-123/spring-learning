package com.demo.pattern;

public class VerboseGreetingProxy implements IGreet{
    private FormalGreeting formalGreeting;

    public VerboseGreetingProxy(FormalGreeting formalGreeting) {
        this.formalGreeting = formalGreeting;
    }

    @Override
    public String greet(String name) {
        return formalGreeting.greet(name).concat(" How Are You Doing?");
    }

}
