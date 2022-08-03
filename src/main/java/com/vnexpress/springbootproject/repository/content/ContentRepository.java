package com.vnexpress.springbootproject.repository.content;

import com.vnexpress.springbootproject.entity.content.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List <Content> findByTitleContentLike (String title);
    

}
