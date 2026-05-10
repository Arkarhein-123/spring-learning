package com.arkar;

import com.arkar.config.AppConfig;
import com.arkar.model.Comment;
import com.arkar.service.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CommentService service = context.getBean(CommentService.class);

        Comment comment = new Comment();
        comment.setText("Hello Gays");
        comment.setAuthor("Arkar Hein");

        service.publicComment(comment);
    }
}
