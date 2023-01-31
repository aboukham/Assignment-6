package com.example.Assignment6.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "logs")

public class Log {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    long id;
    private String principal;
    private String operation;
    private String endPoint;
    private String method;
    private LocalDateTime requestTime;
}
