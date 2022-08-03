package com.vnexpress.springbootproject.service;


import com.vnexpress.springbootproject.entity.user.User;
import com.vnexpress.springbootproject.repository.user.UserRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public User checkLogin(String username, String pass) {
        try {
            User user = userRepository.findByUsernameAndPassword(username, pass);
            if (user != null) {
                return user;
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
//    public List<User> getAllUser(){
//        try{
//            return userRepository.findAll();
//        }
//        catch (Exception e){
//            System.out.println("cant getAll       ");
//            e.printStackTrace();
//        }
//        return null;
//    }
}
