package com.example.movieapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "createdUserId")
    private Long createdUserId;

    @Column(name = "createdUserName")
    private String createdUserName;

    @Column(name = "comments")
    private String comments;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "createTimestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTimestamp;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movieId")
    @JsonBackReference// Fixed column name
    private Movie movie;
}