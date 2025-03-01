package com.example.movieapp.entity;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movie")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movieId", updatable = false)
    private Long movieId;

    @Column(name = "name")
    private String name;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Column(name = "story")
    private String story;

    @Column(name = "base64Img")
    private String base64Img;

    @Column(name = "createdBy")
    private Integer createdBy;

    private boolean active;

    @Column(name = "createdTimestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTimestamp;

    @Column(name = "lastUpdtTimestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdtTimestamp;

    @Enumerated(EnumType.STRING)
    private Languages language;

    @Enumerated(EnumType.STRING)
    private Genres genre;


    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Ratings rating;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Review> reviews = new ArrayList<>();

}