package com.arkar.proxies;

import com.arkar.model.Comment;

public interface CommentNotificationProxy {
    void sendComment(Comment comment);
}
