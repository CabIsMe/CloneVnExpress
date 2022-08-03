package com.vnexpress.springbootproject.dto;

import com.vnexpress.springbootproject.entity.user.Role;
import lombok.Data;
import javax.persistence.*;


@Table(name = "useradmin")
@Data
public class UserDto {
    private Long id;
    private String username;
    private Long group_id;

}
