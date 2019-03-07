package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Blog;
import com.atticusrains.multiblog.models.Post;
import com.atticusrains.multiblog.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BlogDAOImpl implements BlogDAO {

    @Autowired
    PostDAO postDAO;

    @Override
    public Blog getByUser(UserInfo user) {
        return null;
    }

    @Override
    public Blog getByTitle(String title) {
        return null;
    }

    @Override
    public boolean blogExists(String title) {
        return false;
    }

    @Override
    public List<Blog> findAll() {
        return null;
    }

    @Override
    public void save() {

    }
}
