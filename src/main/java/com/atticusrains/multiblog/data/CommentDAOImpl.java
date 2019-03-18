package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Comment;
import com.atticusrains.multiblog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private PostDAO postDAO;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> findAll() {
        return entityManager.createQuery("SELECT FROM Comment").getResultList();
    }

    @Override
    public List<Comment> findByPost(Post post){
        int postId = post.getId();
        return entityManager.createQuery("SELECT c FROM Comment AS c LEFT JOIN c.post AS p WHERE p.id=?").setParameter(1, postId).getResultList();
    }

    @Override
    public List<Comment> findByParentComment(Comment parentComment){
        return entityManager.createQuery("SELECT c FROM Comment as c WHERE c.parentComment=?").setParameter(1, parentComment).getResultList();
    }

    @Override
    public void save(Comment comment, Post post) {
        comment.setPost(post);
        entityManager.persist(comment);
    }

    @Override
    public void saveSubcomment(Comment comment, Comment parentComment){
        parentComment.setIsParent(true);
        comment.setParentComment(parentComment);
        entityManager.persist(comment);
    }
}
