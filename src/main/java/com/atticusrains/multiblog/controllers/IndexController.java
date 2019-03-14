package com.atticusrains.multiblog.controllers;

import com.atticusrains.multiblog.data.BlogDAO;
import com.atticusrains.multiblog.data.PostDAO;
import com.atticusrains.multiblog.data.UserDAO;
import com.atticusrains.multiblog.models.Post;
import com.atticusrains.multiblog.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PostDAO postDAO;

    @Autowired
    BlogDAO blogDAO;

    @RequestMapping(value = "/")
    public String index(Model model){
        List<Post> posts = postDAO.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    @RequestMapping(value = "/admin")
    public String adminPage(){
        return "admin";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String displaySignupForm(Model model){
        model.addAttribute("user", new UserInfo());
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute("user") @Valid UserInfo newUser, @RequestParam(name = "confirmPassword") String confirmPassword, BindingResult bindingResult, Model model){

        if(userDAO.userExists(newUser.getUsername())){
            bindingResult.addError(new FieldError("user","username", "Username is in use"));
        } else if (!newUser.getPassword().equals(confirmPassword)){
            bindingResult.addError(new FieldError("user", "password", "Passwords do not match"));
        }
        if(bindingResult.hasErrors()){
            return "signup";
        }

        newUser.setEnabled((short)1);
        newUser.setRole("ROLE_USER");
        userDAO.save(newUser);
        return "redirect:";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.GET)
    public String displayNewPostForm(Model model){
        model.addAttribute("post", new Post());
        return "newpost";
    }

    @RequestMapping(value = "/newpost", method = RequestMethod.POST)
    public String processNewPostForm(@ModelAttribute("post") @Valid Post post, Model model, Authentication authentication){

        if(authentication == null){
            return "login";
        }

        UserInfo user = userDAO.getActiveUser(authentication.getName());
        post.setUserId(user.getId());
        post.setBlog(user.getBlog());
        if(post.getBody().length() > 255){
            post.setBodyExcerpt(post.getBody().substring(0,250).concat("..."));
        } else { post.setBodyExcerpt(post.getBody());}
        post.setUrlFriendlyTitle(post.cleanString(post.getTitle()));
        postDAO.save(post);
        return "redirect:/b/" + user.getUsername() + "/" + post.getUrlFriendlyTitle();
    }

    @RequestMapping(value = "/403")
    public String Error403(){
        return "403";
    }

    @RequestMapping(value = "/404")
    public String Error404(){
        return "404";
    }
}
