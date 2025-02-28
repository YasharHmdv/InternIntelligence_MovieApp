package com.example.movieapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "ratings")
public class Ratings {

	@Id
	@Column(name = "movieId")
	private Integer movieId;

	@Column(name = "likes")
	private Integer likes;

	@Column(name = "dislike")
	private Integer dislike;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "totalRatings")
	private Integer totalRatings;

	@Column(name = "createTimestamp")
	private Date createTimestamp;
}