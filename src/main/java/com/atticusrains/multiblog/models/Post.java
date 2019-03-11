package com.atticusrains.multiblog.models;

import org.apache.tomcat.jni.Time;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "blog_posts")
public class Post {

    public Post() {
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Post(String title, Blog blog){
        this.title = title;
        this.blog = blog;
        this.urlFriendlyTitle = cleanString(title);
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public Post(String title, Blog blog, String body){
        this.title = title;
        this.blog = blog;
        this.urlFriendlyTitle = cleanString(title);
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.body = body;
        if(body.length() > 250) {
            this.bodyExcerpt = body.substring(250);
        }else{
            this.bodyExcerpt = body;
        }
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    private Blog blog;

    private String title;

    private String urlFriendlyTitle;

    private String body;

    private String bodyExcerpt;

    private Timestamp timestamp;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<Comment> comments = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlFriendlyTitle() {
        return urlFriendlyTitle;
    }

    public void setUrlFriendlyTitle(String urlFriendlyTitle) {
        this.urlFriendlyTitle = urlFriendlyTitle;
    }

    public String getBody() {
        return body;
    }

    public String getBodyExcerpt() {
        return bodyExcerpt;
    }

    public void setBodyExcerpt(String bodyExceprt) {
        this.bodyExcerpt = bodyExceprt;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String cleanString(String string){
        String urlFriendlyString = string.toLowerCase().replaceAll("[^a-zA-Z0-9]","").replaceAll(" ", "-");
        return urlFriendlyString;
    }
}
