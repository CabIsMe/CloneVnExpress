package com.vnexpress.springbootproject.repository.user;

import com.vnexpress.springbootproject.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import org.springframework.security.core.userdetails.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword( String username, String password);
}
