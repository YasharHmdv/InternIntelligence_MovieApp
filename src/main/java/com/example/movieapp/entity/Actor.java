package com.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;


@Entity(name = "actor")
@Table(name = "actor")
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String name;

    @Column(name = "imageUrl", nullable = true, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;
}