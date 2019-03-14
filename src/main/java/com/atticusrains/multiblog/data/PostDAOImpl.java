package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Blog;
import com.atticusrains.multiblog.models.Comment;
import com.atticusrains.multiblog.models.Post;
import com.atticusrains.multiblog.models.UserInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PostDAOImpl implements  PostDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Post> findAll() {
        return entityManager.createQuery("FROM Post").getResultList();
    }

    @Override
    public List<Post> findAllByUser(UserInfo user) {
        return entityManager.createQuery("SELECT post FROM Post WHERE userId=?").setParameter(1, user.getId()).getResultList();
    }

    @Override
    public List<Post> findAllByBlog(Blog blog) {
        return entityManager.createQuery("SELECT post FROM Post WHERE blogId=?").setParameter(1, blog.getId()).getResultList();
    }

    @Override
    public Post findByTitle(String title, int blogId) {
        return (Post)entityManager.createQuery("SELECT post FROM Post as post left join post.blog as blog WHERE post.urlFriendlyTitle=? and blog.id=?").setParameter(2, blogId).setParameter(1, title).getSingleResult();
    }

    @Override
    public Post findById(int postId, int blogId){
        return (Post)entityManager.createQuery("SELECT post FROM Post AS p LEFT JOIN p.blog AS b WHERE p.id=? and b.id=?").setParameter(1, postId).setParameter(2, blogId).getSingleResult();
    }

    @Override
    public boolean postExists(String title, int blogId){
        List<Post> resultList = entityManager.createQuery("SELECT post FROM Post as post left join post.blog as blog WHERE post.urlFriendlyTitle=? and blog.id=?").setParameter(2, blogId).setParameter(1, title).getResultList();
        if(resultList.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void save(Post newPost) {
        entityManager.persist(newPost);
    }
}
