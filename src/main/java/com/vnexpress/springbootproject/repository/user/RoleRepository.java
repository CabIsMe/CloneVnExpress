package com.vnexpress.springbootproject.repository.user;

import com.vnexpress.springbootproject.dto.RoleDto;
import com.vnexpress.springbootproject.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
