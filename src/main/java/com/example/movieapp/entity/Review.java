package com.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column(name = "movieId")
    private Integer movieId;

    @Column(name = "createdUserId")
    private Integer createdUserId;

    @Column(name = "createdUserName")
    private String createdUserName;

    @Column(name = "likeMovie")
    private String likeMovie;

    @Column(name = "comments")
    private String comments;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "createTimestamp")
    private Date createTimestamp;
}