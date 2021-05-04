package com.quiz.service.impl;


import com.quiz.entities.Answer;
import com.quiz.entities.dtos.AnswerDto;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.exception.NotFoundException;
import com.quiz.repository.AnswerRepository;
import com.quiz.service.interf.AnswerService;
import com.quiz.service.interf.ConverterDto;
import com.quiz.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionService questionService;
    private final ConverterDto<Answer, AnswerDto> answerConverterDto;

    @Override
    public void addAnswerToQuestion(AnswerDto answerDto, Integer questionId) throws NotFoundException {
        QuestionDto questionDto = questionService.findQuestionById(questionId);
//        answerRepository.save(answerConverterDto.fromDtoToEntity(answerDto));
        answerDto.setQuestionDto(questionDto);
        Set<AnswerDto> answers = questionDto.getAnswers();
        answers.add(answerDto);
        questionDto.setAnswers(answers);
        questionService.updateQuestion(questionDto);
    }

    @Override
    public void updateAnswer(AnswerDto answerDto) {
        answerRepository.save(answerConverterDto.fromDtoToEntity(answerDto));
    }

    @Override
    public AnswerDto findAnswerById(Integer id) throws NotFoundException {
        return answerConverterDto.fromEntityToDto(answerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Answer not found with id: " + id)));
    }

    @Override
    public Set<AnswerDto> findAnswersByQuestionId(Integer questionId) throws NotFoundException {
        return new HashSet<>(questionService.findQuestionById(questionId)
                .getAnswers());
    }

    @Override
    public void deleteAnswerById(Integer id) {
        answerRepository.deleteById(id);
    }

}
