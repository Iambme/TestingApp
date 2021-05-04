package com.quiz.repository;


import com.quiz.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    List<Quiz> findByUserId(Integer userId);

    void deleteQuizzesByUserId(Integer userId);
}
