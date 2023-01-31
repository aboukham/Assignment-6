package com.example.Assignment6.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String email;
    String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    List<Post> posts;

    @ManyToMany(fetch = FetchType.EAGER)
    List<Role> roles;

}
