package com.quiz.service.interf;


import com.quiz.entities.dtos.AnswerDto;
import com.quiz.exception.NotFoundException;

import java.util.Set;

public interface AnswerService {
    void addAnswerToQuestion(AnswerDto answerDto, Integer questionId) throws NotFoundException;

    void updateAnswer(AnswerDto answerDto);

    AnswerDto findAnswerById(Integer id) throws NotFoundException;

    Set<AnswerDto> findAnswersByQuestionId(Integer questionId) throws NotFoundException;

    void deleteAnswerById(Integer id);
}
