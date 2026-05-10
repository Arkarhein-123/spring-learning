package com.arkar;

import com.arkar.config.AppConfig;
import com.arkar.model.Comment;
import com.arkar.proxies.CommentNotificationProxy;
import com.arkar.proxies.EmailCommentNotificationProxy;
import com.arkar.repositories.CommentRepository;
import com.arkar.repositories.DBCommentRepository;
import com.arkar.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Comment comment = new Comment();
        comment.setAuthor("Arkar");
        comment.setText("Your are such a good boy!");

        CommentService commentService = context.getBean(CommentService.class);

        commentService.publishComment(comment);


    }
}
