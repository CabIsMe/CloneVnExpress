package com.vnexpress.springbootproject.controller;


import com.vnexpress.springbootproject.entity.content.Content;
//import com.vnexpress.springbootproject.file.WriteFileExcel;
import com.vnexpress.springbootproject.rabbitmq.MessagingConfig;
import com.vnexpress.springbootproject.dto.ContentDto;
import com.vnexpress.springbootproject.service.ContentServiceImpl;
import com.vnexpress.springbootproject.service.IContentService;
import com.vnexpress.springbootproject.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private IContentService iContentService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    @GetMapping("/home")
    public String getListContent(Model model, HttpServletResponse response) throws IOException{
        List<ContentDto> list=iContentService.listContent();
        model.addAttribute("listCt",list);
        return "index";
    }


    @GetMapping("/addcontent")
    public String addContent(Model model) {
        model.addAttribute("content", new ContentDto());
        return "add";
    }
    Integer i=0;
    @PostMapping("/addcontent")
    public String creationSubmit(ContentDto contentDto, Model model, @RequestParam("image") MultipartFile image) throws IOException{
        Path path=Paths.get("src/main/resources/static/images/");
        try{
            InputStream inputStream=image.getInputStream();
            Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            String url="/images/"+image.getOriginalFilename().toLowerCase();
            contentDto.setImageContent(url);
            model.addAttribute("content", contentDto);
            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
            contentDto.setTimeContent(date);


            rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, contentDto);
            logger.error(contentDto.getTitleContent());
            i++;
            logger.info("Message count: "+i.toString());
            iContentService.addContent(contentDto);
            model.addAttribute("message","Success!");
        }catch (Exception e){
            e.printStackTrace();

        }

        return "add";
    }

    @GetMapping("/updatecontent/{id}")
    String updateContentCurrent(@PathVariable("id") Long id, Model model) {
        ContentDto contentDto1=iContentService.get1Content(id);
        model.addAttribute("content",contentDto1);
        return "update";
    }

    @PostMapping("/updatecontent/{id}")
    String updateContent(@PathVariable("id") Long id, Model model, ContentDto contentDto, @RequestParam("image") MultipartFile image)
            throws IOException{
        Path path=Paths.get("src/main/resources/static/images/");
        if(image.isEmpty()){
            ContentDto contentDto1=iContentService.get1Content(id);
            contentDto.setImageContent(contentDto1.getImageContent());
        }
        else {
            InputStream inputStream=image.getInputStream();
            Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            String url="/images/"+image.getOriginalFilename().toLowerCase();
            contentDto.setImageContent(url);
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        contentDto.setTimeContent(date);
        model.addAttribute("content",contentDto);
        iContentService.updateContent(id, contentDto);
        return "redirect:/content/home";
    }

    @GetMapping("/getlistcontent/{id}")
    public String deleteContent(@PathVariable("id") Long id) {
        if(iContentService.deleteContent(id)){
            return "redirect:/content/home";
        }
        return "index";
    }
    @GetMapping("/getlistcontent/search")
    public String getListByTitle(Model model,@RequestParam("keyword") String keyword){
        model.addAttribute("keyword", keyword);
        List <ContentDto> contentDtos=iContentService.getListByTitle("%"+keyword+"%");
        model.addAttribute("listCt", contentDtos);
        return "index";
    }

//    public List<Content> getListContent(){
//        return iContentService.listContent();
//    }






}
