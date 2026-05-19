package com.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) { // Fixed here
        System.out.println("Spring AOP Test....");

        var context = new AnnotationConfigApplicationContext(AppConfig.class);
        var commentService = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Hello bro.");
        comment.setAuthor("Arkar");

        commentService.publishCommenet(comment);
    }
}