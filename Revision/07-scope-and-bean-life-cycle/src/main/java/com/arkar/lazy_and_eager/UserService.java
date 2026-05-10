package com.arkar.lazy_and_eager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  CommentRepository commentRepository;

    public CommentRepository getCommentRepository(){
        return commentRepository;
    }
}
