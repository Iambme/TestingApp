package com.quiz.service.interf;


import com.quiz.entities.dtos.QuestionDto;
import com.quiz.entities.dtos.QuizDto;
import com.quiz.exception.NotFoundException;

import java.util.List;

public interface QuizService {
    void addQuiz(QuizDto quizDto);

    void addQuestionToQuizById(Integer quizId, QuestionDto questionDto) throws NotFoundException;

    QuizDto findQuizById(Integer id) throws NotFoundException;

    List<QuizDto> findAll();

    List<QuizDto> findQuizByUserId(Integer userId);

    void updateQuiz(QuizDto quizDto);

    void deleteQuizById(Integer id);

    void deleteQuizByUserId(Integer userId);
}
