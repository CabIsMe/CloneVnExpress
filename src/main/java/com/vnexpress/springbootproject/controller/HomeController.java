package com.vnexpress.springbootproject.controller;

import com.vnexpress.springbootproject.dto.ContentDto;
import com.vnexpress.springbootproject.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vnexpress")
public class HomeController {
    int x=1;
    @Autowired
    private IContentService iContentService;
    @GetMapping("/home")
    public String getFirstHomePage(Model model){
        List<ContentDto> list=iContentService.getPageContent(0);
        model.addAttribute("listCt",list);
        model.addAttribute("x",x);

        return "home";
    }
    @GetMapping("/home/{start}")
    public String loadMoreHomePage(Model model, @PathVariable("start") Integer start){
        int totalElement=iContentService.listContent().size();
        if(x<totalElement/3) x++;
        model.addAttribute("x",x);
        List<ContentDto> list=iContentService.getPageContent(start);
        model.addAttribute("currentPage",start);
        model.addAttribute("listCt",list);

        return "home";
    }


}
