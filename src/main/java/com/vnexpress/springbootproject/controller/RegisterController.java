package com.vnexpress.springbootproject.controller;

import com.vnexpress.springbootproject.entity.user.Role;
import com.vnexpress.springbootproject.entity.user.User;
import com.vnexpress.springbootproject.repository.user.RoleRepository;
import com.vnexpress.springbootproject.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class RegisterController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/register.html")
    public String showRegistrationForm(Model model,User user) {
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("register.html")
    public String processRegister(Model model,User user) {
        model.addAttribute("user",user);

        if(user.getUsername()==null || user.getPassword()==null )   return "register";
        if(userRepository.findByUsername(user.getUsername())!=null){
            return "register";
        }
        userRepository.save(user);
        return "login.html";
    }
}
