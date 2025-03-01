package com.example.movieapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public enum Genres {
	ACTION,
	ADVENTURE,
	COMEDY,
	DRAMA,
	FANTASY,
	HORROR,
	MYSTERY,
	ROMANCE,
	SCI_FI,
	THRILLER,
	ANIMATION,
	DOCUMENTARY
}