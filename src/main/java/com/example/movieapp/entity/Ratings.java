package com.example.movieapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ratings")
public class Ratings {

	@Id
	@Column(name = "ratingId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ratingId;

	@Column(name = "likes")
	private Integer likes;

	@Column(name = "dislike")
	private Integer dislike;

	@Column(name = "rating")
	private Double rating;

	@Column(name = "totalRatings")
	private Integer totalRatings;

	@Column(name = "createTimestamp")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTimestamp;

	@OneToOne
	@JoinColumn(name = "movieId", referencedColumnName = "movieId")
	@JsonIgnore
	private Movie movie;
}