package com.atticusrains.multiblog.models;

import javax.persistence.*;
import java.sql.Timestamp;

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
