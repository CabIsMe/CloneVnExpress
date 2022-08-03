package com.vnexpress.springbootproject.dto;


import com.vnexpress.springbootproject.entity.user.User;
import lombok.Data;

import java.util.Set;


@Data
public class RoleDto {
    private Long role_id;
    private String role_name;
    private String role_rights;
    private Set<UserDto> users;
}
