package com.example.movieapp.repository;

import com.example.movieapp.entity.Languages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Languages,Long> {
}
