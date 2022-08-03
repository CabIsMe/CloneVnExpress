package com.vnexpress.springbootproject.service;

import com.vnexpress.springbootproject.dto.ContentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IContentService {
    public ContentDto addContent(ContentDto content);
    public List<ContentDto> listContent();
    public List<ContentDto> getPageContent(Integer x);
    public void saveImage(MultipartFile imageFile);
    public ContentDto updateContent(Long id, ContentDto contentDto);
    public ContentDto get1Content(Long id);
    public boolean deleteContent(Long id);
    List <ContentDto> getListByTitle(String title);

}
