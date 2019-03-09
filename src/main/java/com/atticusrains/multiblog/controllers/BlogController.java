package com.atticusrains.multiblog.controllers;

import com.atticusrains.multiblog.data.BlogDAO;
import com.atticusrains.multiblog.data.PostDAO;
import com.atticusrains.multiblog.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/b")
public class BlogController {

    @Autowired
    BlogDAO blogDAO;
    @Autowired
    PostDAO postDAO;

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
            model.addAttribute("post", postDAO.findByTitle(postTitle, blog.getId()));
            return "postView";
        }
        return "404";
    }



}
