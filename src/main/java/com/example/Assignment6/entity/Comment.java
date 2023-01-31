package com.example.Assignment6.entity;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name= "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    @ManyToOne
    Post post;
}
