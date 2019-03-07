package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Blog;
import com.atticusrains.multiblog.models.Post;
import com.atticusrains.multiblog.models.UserInfo;

import java.util.List;

public interface PostDAO {
    List<Post> findAll();
    List<Post> findAllByUser(UserInfo user);
    List<Post> findAllByBlog(Blog blog);
    Post findByTitle(String title, int blogId);
    boolean postExists(String title, int blogId);
    void save(Post newPost);
}
