package com.arkar.repositories;

import com.arkar.model.Comment;

public interface CommentRepository {
    void storeComment(Comment comment);
}
