package com.vnexpress.springbootproject.entity.content;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="topic")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id", columnDefinition = "serial")
    private Long topic_id;
    private String topic_name;
}
