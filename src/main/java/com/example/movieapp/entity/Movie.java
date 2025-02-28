package com.example.movieapp.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "movie")
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "releaseYear")
    private Integer releaseYear;

    @Column(name = "story")
    private String story;

    @Column(name = "base64Img")
    private String base64Img;

    @Column(name = "languageId")
    private Integer languageId;

    @Column(name = "genreId")
    private Integer genreId;

    @Column(name = "createdBy")
    private Integer createdBy;

    @Column(name = "active")
    private String active;

    @Column(name = "createdTimestamp")
    private Date createdTimestamp;

    @Column(name = "lastUpdtTimestamp")
    private Date lastUpdtTimestamp;

    @Transient
    private String genre;

    @Transient
    private String language;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Ratings rating;

    @Transient
    private List<Review> reviews;

}