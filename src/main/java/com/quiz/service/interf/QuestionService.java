package com.quiz.service.interf;


import com.quiz.entities.dtos.QuestionDto;
import com.quiz.exception.NotFoundException;

import java.util.Set;

public interface QuestionService {
    void addQuestion(QuestionDto questionDto);

    void updateQuestion(QuestionDto questionDto);

    QuestionDto findQuestionById(Integer id) throws NotFoundException;

    Set<QuestionDto> findAll();

    void deleteQuestionById(Integer id);
}
