package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class PaidGame {
    @Id
    private String id;
    private String name;
    private String url;
    private String releaseDate;
    private String artistName;
}

