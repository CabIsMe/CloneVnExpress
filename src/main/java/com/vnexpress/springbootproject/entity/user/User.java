package com.vnexpress.springbootproject.entity.user;


import javax.persistence.*;


@Entity
@Table(name = "useradmin")
public class User {
    @Id
    @Column(name = "user_id",columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "group_id")
    private Long group_id;


    public Long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Long group_id) {
        this.group_id = group_id;
    }

    public User (){}
    public User(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public Long isEnabled() {
//        return role_id;
//    }
//
//    public void setEnabled(Long enabled) {
//        this.role_id = enabled;
//    }


}
