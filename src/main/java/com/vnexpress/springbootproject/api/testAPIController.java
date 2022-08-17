package com.vnexpress.springbootproject.api;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vnexpress.springbootproject.dto.ContentDto;
import com.vnexpress.springbootproject.dto.RoleDto;
import com.vnexpress.springbootproject.dto.UserDto;
import com.vnexpress.springbootproject.entity.content.Content;
import com.vnexpress.springbootproject.entity.user.Role;
import com.vnexpress.springbootproject.entity.user.User;

import com.vnexpress.springbootproject.jaeger.JaegerService;
import com.vnexpress.springbootproject.rabbitmq.MessagingConfig;
import com.vnexpress.springbootproject.repository.content.ContentRepository;
import com.vnexpress.springbootproject.repository.user.RoleRepository;
import com.vnexpress.springbootproject.repository.user.UserRepository;
import com.vnexpress.springbootproject.service.ContentServiceImpl;
import com.vnexpress.springbootproject.service.IContentService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
//import vn.com.fpt.foxpay.services.common.exception.BusinessException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/test")
public class testAPIController {

//    @Autowired
//    private IContentService iContentService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private JaegerService jaegerService;

    public testAPIController(JaegerService jaegerService) {
        this.jaegerService = jaegerService;
    }


    private static final Logger logger = LoggerFactory.getLogger(testAPIController.class);
    @GetMapping("/getlisttest")
    public List<ContentDto> Test(){
        List<Content> contents=contentRepository.findAll();
        return contents.stream().map(content -> modelMapper.map(content,ContentDto.class)).collect(Collectors.toList());
    }
    @GetMapping("/getallrole")
//    public List<Role> roleList(){
//        return roleRepository.findAll();
//    }
    public List<RoleDto> roles(){
        List<Role> roles=roleRepository.findAll();
        return roles.stream().map(role -> modelMapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }
    @GetMapping("get1role")
    public RoleDto roleDto(@RequestParam("id") Long id){
        Role role = roleRepository.findById(id).get();
        Date date= new Date();

        try{
            logger.info("send...");
            rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, role);
            jaegerService.get(id);
//            logger.info("THE MESSAGE : {}",rabbitTemplate.getMessageConverter());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return modelMapper.map(roleRepository.getReferenceById(id),RoleDto.class);
    }

    @GetMapping("/getalluser")
    public List<UserDto> users(){

        List<User> users=userRepository.findAll();
        // JSONObject to JSONArrays
        JSONArray ja = new JSONArray();
        for (User user: users){
            JSONObject pData= new JSONObject(user);
            ja.put(pData);
        }
        logger.info(ja.toString());
        // List TO JSONArray
        JSONArray ja2 = new JSONArray(users);
        logger.info(ja2.toString());
        return users.stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }
    @GetMapping("/get1user")
    public UserDto userDto(@RequestParam("id") Long id){
        return modelMapper.map(userRepository.getReferenceById(id), UserDto.class);
    }

    @GetMapping("/hello")
    ResponseEntity<UserDto> hi(@RequestParam("id") Long id) {

        logger.info("Id User: {}", id);
        if(userRepository.findById(id).orElse(null)==null){
            logger.error("Not found id: {}",id);
            return ResponseEntity.notFound().build();
        }
        UserDto userDto=modelMapper.map(userRepository.getReferenceById(id), UserDto.class);
        JSONObject pData= new JSONObject();
        pData.put("USER",userDto.getUsername());
        logger.info("logging JSONObject {}",pData);
        return ResponseEntity.ok(userDto);
    }

}
