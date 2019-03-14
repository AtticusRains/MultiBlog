package com.atticusrains.multiblog.controllers;

import com.atticusrains.multiblog.data.BlogDAO;
import com.atticusrains.multiblog.data.CommentDAO;
import com.atticusrains.multiblog.data.PostDAO;
import com.atticusrains.multiblog.models.Blog;
import com.atticusrains.multiblog.models.Comment;
import com.atticusrains.multiblog.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/b")
public class BlogController {

    @Autowired
    private BlogDAO blogDAO;
    @Autowired
    private PostDAO postDAO;
    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping(value = "/{blogTitle}")
    public String displayBlog(@PathVariable String blogTitle, Model model){
        if(blogDAO.blogExists(blogTitle)){
            model.addAttribute("posts", blogDAO.getByTitle(blogTitle).getPosts());
            return "blogview";
        }
        return "404";
    }

    @RequestMapping(value = "/{blogTitle}/{postTitle}")
    public String displayPost(@PathVariable(value = "blogTitle") String blogTitle, @PathVariable(value = "postTitle") String postTitle, Model model){
        Blog blog = blogDAO.getByTitle(blogTitle);
        if(blogDAO.blogExists(blogTitle) && postDAO.postExists(postTitle, blog.getId())){
            Post post = postDAO.findByTitle(postTitle, blog.getId());
            List<Comment> comments = commentDAO.findByPost(post);
            model.addAttribute("post", post);
            model.addAttribute("comments", comments);
            model.addAttribute("newComment", new Comment());
            return "postview";
        }
        return "404";
    }

    @RequestMapping(value = "/{blogTitle}/{postTitle}/comment")
    public String processCommentForm(@ModelAttribute("comment")Comment comment, @PathVariable(value = "blogTitle") String blogTitle, @PathVariable(value = "postTitle") String postTitle, Model model){
        commentDAO.save(comment, postDAO.findByTitle(postTitle, blogDAO.getByTitle(blogTitle).getId()));
        return "postview";
    }



}
