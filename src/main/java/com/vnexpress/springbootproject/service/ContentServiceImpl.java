package com.vnexpress.springbootproject.service;

import com.vnexpress.springbootproject.rabbitmq.MessagingConfig;
import com.vnexpress.springbootproject.dto.ContentDto;
import com.vnexpress.springbootproject.entity.content.Content;
import com.vnexpress.springbootproject.repository.content.ContentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service("singleTransactionsService")
public class ContentServiceImpl implements IContentService{
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    @Override
    public ContentDto addContent(ContentDto contentDto) {

        return modelMapper.map(contentRepository.save(modelMapper.map(contentDto, Content.class)), ContentDto.class);
    }

    @Override
    public List<ContentDto> listContent() {
        List <Content> contents=contentRepository.findAll();
        return contents.stream().map(content -> modelMapper.map(content,ContentDto.class)).collect(Collectors.toList());
    }

//    Pageable firstPageWith3Elements= PageRequest.of(0,6);

    @Override
    public List<ContentDto> getPageContent(Integer x){
        Page<Content> content1=contentRepository.findAll(PageRequest.of(0,(x+1)*3));
        List<Content> contents=content1.getContent();
        for(Content content: contents ){
            logger.warn(content.getTitle());
        }
        return contents.stream().map(content -> modelMapper.map(content,ContentDto.class)).collect(Collectors.toList());
    }


    @Override
    public void saveImage(MultipartFile imageFile) {

    }

    @Override
    public ContentDto updateContent(Long id, ContentDto contentDto) {
        if(contentDto!=null){
            Content content= contentRepository.getReferenceById(id);
            if(content!=null){
                content.setTitle(contentDto.getTitleContent());
                content.setMainContent(contentDto.getMainContent());
                content.setImageContent(contentDto.getImageContent());
                content.setTimeContent(contentDto.getTimeContent());
                contentRepository.save(content);
                return modelMapper.map(content, ContentDto.class);
            }

        }
        return null;
    }

    @Override
    public ContentDto get1Content(Long id) {
        Content content=contentRepository.getReferenceById(id);
        
        if(content!=null){
            return modelMapper.map(content, ContentDto.class);
        }
        return null;
    }

    @Override
    public boolean deleteContent(Long id) {
        Content content=contentRepository.getReferenceById(id);
        if(content!=null){
            contentRepository.delete(content);
            return true;
        }
        return false;
    }

    @Override
    public List<ContentDto> getListByTitle(String title) {
        List <Content> contents=contentRepository.findByTitleContentLike(title);
        if(contents.isEmpty() || contents==null){
            String tmp=title.charAt(0)+ title.substring(1, 2).toUpperCase() + title.substring(2);
            logger.warn(tmp);
            contents=contentRepository.findByTitleContentLike(tmp);
        }
        return contents.stream().map(content -> modelMapper.map(content,ContentDto.class)).collect(Collectors.toList());
    }



}
