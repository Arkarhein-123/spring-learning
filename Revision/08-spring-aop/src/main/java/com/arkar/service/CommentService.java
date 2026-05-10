package com.arkar.service;

import com.arkar.model.Comment;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CommentService {
    private Logger logger = Logger.getLogger(CommentService.class.getName());

    public void publicComment(Comment comment) {
        logger.info("Publishing Comment : " + comment.getText());
    }

}
