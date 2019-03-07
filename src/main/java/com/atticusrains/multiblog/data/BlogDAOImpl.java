package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Blog;
import com.atticusrains.multiblog.models.Post;
import com.atticusrains.multiblog.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class BlogDAOImpl implements BlogDAO {

    @PersistenceContext
    EntityManager blogEntityManager;

    @Autowired
    PostDAO postDAO;

    @Override
    public Blog getByUser(UserInfo user) {
        return null;
    }

    @Override
    public Blog getByTitle(String title) {
       return (Blog)blogEntityManager.createQuery("SELECT blog FROM Blog blog WHERE title=?").setParameter(1, title).getSingleResult();
    }

    @Override
    public boolean blogExists(String title) {
        List<?> list = blogEntityManager.createQuery("SELECT blog FROM Blog blog WHERE title=?").setParameter(1,title).getResultList();
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public List<Blog> findAll() {
       return blogEntityManager.createQuery("FROM Blog").getResultList();
    }

    @Override
    public void save(Blog blog) {
        blogEntityManager.persist(blog);
    }
}
