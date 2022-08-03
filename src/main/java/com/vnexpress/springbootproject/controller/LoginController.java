package com.vnexpress.springbootproject.controller;


import com.vnexpress.springbootproject.dto.ContentDto;
import com.vnexpress.springbootproject.entity.user.User;
import com.vnexpress.springbootproject.service.IContentService;
import com.vnexpress.springbootproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private IContentService iContentService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login.html")
    public String login(){

        return "login";
    }

    @PostMapping("login.html")
    public String home(String username,String password, Model model, HttpServletRequest request){

        model.addAttribute("username",username);
        model.addAttribute("password",password);
        User user=userService.checkLogin(username,password);
        if(user==null){

            return "login.html";
        }
//        User user= new User("user","123");
        HttpSession session=request.getSession();
        session.setAttribute("user",user);
        List<ContentDto> list=iContentService.listContent();
        model.addAttribute("listCt",list);

        return "redirect:/content/home";
    }


}
