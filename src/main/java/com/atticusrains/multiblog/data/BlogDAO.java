package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Blog;
import com.atticusrains.multiblog.models.Post;
import com.atticusrains.multiblog.models.UserInfo;

import java.util.List;


public interface BlogDAO {
    Blog getByUser(UserInfo user);
    Blog getByTitle(String title);
    boolean blogExists(String title);
    List<Blog> findAll();
    void save(Blog blog);

}
