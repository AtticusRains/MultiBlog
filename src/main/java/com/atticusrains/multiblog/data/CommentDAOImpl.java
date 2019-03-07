package com.atticusrains.multiblog.data;

import com.atticusrains.multiblog.models.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {
    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public void save() {

    }
}
