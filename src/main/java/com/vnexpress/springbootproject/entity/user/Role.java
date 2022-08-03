package com.vnexpress.springbootproject.entity.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.*;

import javax.persistence.*;


@Entity
@Data
@Table(name = "grp")
public class Role {
    @Id
    @Column(name = "group_id",columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;

    @Column(name = "group_name")
    private String role_name;

    @Column(name = "group_rights")
    private String role_rights;


    @OneToMany(
            targetEntity = User.class,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<User> users;

    public Role(Set<User> users) {
        this.users = users;
    }

    public Role() {

    }

}