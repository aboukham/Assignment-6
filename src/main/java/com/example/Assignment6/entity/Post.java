package com.example.Assignment6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "post_id")
    @TableGenerator(name = "post_id", table = "post_id", initialValue = 5)
    @Column(nullable = false)
    long id;
     String title;
     String content;
     String author;
     @ManyToOne
     @JoinColumn
     User user;

     @OneToMany(cascade = CascadeType.ALL)
     @JoinColumn(name = "post_id")
     List<Comment> comments;
}

