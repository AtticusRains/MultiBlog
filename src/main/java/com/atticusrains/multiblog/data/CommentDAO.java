package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Comment;
import com.atticusrains.multiblog.models.Post;

import java.util.List;

public interface CommentDAO {
    List<Comment> findAll();
    List<Comment> findByPost(Post post);
    void save(Comment comment, Post post);
}
