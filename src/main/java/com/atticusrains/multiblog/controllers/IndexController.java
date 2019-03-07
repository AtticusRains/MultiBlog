package com.atticusrains.multiblog.controllers;

import com.atticusrains.multiblog.data.BlogDAO;
import com.atticusrains.multiblog.data.PostDAO;
import com.atticusrains.multiblog.data.UserDAO;
import com.atticusrains.multiblog.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class IndexController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    PostDAO postDAO;

    @Autowired
    BlogDAO blogDAO;

    @RequestMapping(value = "/")
    public String index(){
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
        String confirmPassword = "";
        model.addAttribute("confirmPassword", confirmPassword);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processSignupForm(@ModelAttribute("user") @Valid UserInfo newUser, @ModelAttribute("confirmPassword") String confirmPassword,BindingResult bindingResult, Model model){

        if(userDAO.userExists(newUser.getUsername())){
            bindingResult.addError(new FieldError("user","username", "Username is in use"));
        }// else if (!newUser.getPassword().equals(newUser.getPasswordConfirm())){
       //     bindingResult.addError(new FieldError("user", "password", "Passwords do not match"));
       // }
        if(bindingResult.hasErrors()){
            return "signup";
        }

        newUser.setEnabled((short)1);
        newUser.setRole("ROLE_USER");
        userDAO.save(newUser);
        return "redirect:";
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
