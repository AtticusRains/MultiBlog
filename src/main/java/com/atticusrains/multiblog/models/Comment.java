package com.atticusrains.multiblog.models;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    public Comment(){
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue
    private int id;
    private Timestamp timestamp;
    private String body;

    @ManyToOne
    private Post post;

    @ManyToOne
    private UserInfo user;

    @OneToMany(mappedBy="parentComment")
    private List<Comment> subComments = new ArrayList<>();

    @ManyToOne
    private Comment parentComment;

    private boolean isParent;

    public void setIsParent(boolean isParent){
        this.isParent = isParent;
    }

    public boolean getIsParent(){
        return isParent;
    }

    public List<Comment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Comment> subComments) {
        this.subComments = subComments;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public UserInfo getUser(){
        return user;
    }

    public void setUser(UserInfo user){
        this.user=user;
    }
}
