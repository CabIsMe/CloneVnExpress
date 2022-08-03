package com.vnexpress.springbootproject.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@ToString
public class ContentDto {
    private Long idContent;
    private String titleContent;
    private String mainContent;
    private String imageContent;
    private Date timeContent;
}
