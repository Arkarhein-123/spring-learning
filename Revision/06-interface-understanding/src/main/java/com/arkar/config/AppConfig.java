package com.arkar.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.arkar")
public class AppConfig {
//    @Bean
//    public CommentRepository commentRepository(){
//        return new DBCommentRepository();
//    }
//
//    @Bean
//    public CommentNotificationProxy commentNotificationProxy(){
//        return new EmailCommentNotificationProxy();
//    }
//
//    @Bean
//    public CommentService commentService(
//            CommentRepository commentRepository, CommentNotificationProxy commentNotificationProxy
//    ){
//        return new CommentService(commentRepository,commentNotificationProxy);
//    }
}
