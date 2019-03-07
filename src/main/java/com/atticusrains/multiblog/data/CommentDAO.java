package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> findAll();
    void save();
}
