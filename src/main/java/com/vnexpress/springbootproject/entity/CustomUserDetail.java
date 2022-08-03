//package com.vnexpress.springbootproject.entity;
//
//import com.vnexpress.springbootproject.entity.user.User;
//import com.vnexpress.springbootproject.repository.user.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Collection;
//
////package com.vnexpress.springbootproject.entity;
////
////import java.util.*;
////
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
////
////
//public class CustomUserDetail implements UserDetails {
//    private User user;
//    public CustomUserDetail(User user){
//        this.user = user;
//    }
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//
//
//
//
//
//
////    private  User user;
////
////    public MyUserDetails(User user){
////        this.user=user;
////    }
////
////
////
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        Set<Role> roles = user.getRoles();
////        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
////
////        for (Role role : roles) {
////            authorities.add(new SimpleGrantedAuthority(role.getName()));
////
////        }
////
////        return authorities;
////    }
////
////    @Override
////    public String getPassword() {
////        return user.getPassword();
////    }
////
////    @Override
////    public String getUsername() {
////        return user.getUsername();
////    }
//    //    @Override
////    public boolean isEnabled() {
////        return user.isEnabled();
////    }
////
////    @Override
////    public boolean isAccountNonExpired() {
////        return true;
////    }
////
////    @Override
////    public boolean isAccountNonLocked() {
////        return true;
////    }
////
////    @Override
////    public boolean isCredentialsNonExpired() {
////        return true;
////    }
////
//
//}
