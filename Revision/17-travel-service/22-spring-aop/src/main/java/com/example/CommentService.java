package com.example;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {
    private Logger logger = Logger.getLogger(CommentService.class.getName());

    public void publishCommenet(Comment comment){
        logger.info("Publishing com.example.Comment : " + comment.getText());
    }
}
