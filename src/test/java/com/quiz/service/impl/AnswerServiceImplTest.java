package com.quiz.service.impl;

import com.quiz.entities.Answer;
import com.quiz.entities.dtos.AnswerDto;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.exception.NotFoundException;
import com.quiz.repository.AnswerRepository;
import com.quiz.service.interf.AnswerService;
import com.quiz.service.interf.ConverterDto;
import com.quiz.service.interf.QuestionService;
import com.quiz.util.AnswerDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static com.quiz.prototype.AnswerPrototype.*;
import static com.quiz.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class AnswerServiceImplTest {
    private final AnswerRepository answerRepository = mock(AnswerRepository.class);
    private final QuestionService questionService = mock(QuestionService.class);
    private ConverterDto<Answer, AnswerDto> answerDtoConverter;
    private AnswerService answerService;

    @BeforeEach
    void setUp() {
        answerDtoConverter = new AnswerDtoConverter();
        answerService = new AnswerServiceImpl(answerRepository, questionService, answerDtoConverter);
    }

    @Test
    void addAnswerToQuestion() throws NotFoundException {
        QuestionDto testQuestion = getQuestionDtoFullParam();
        when(questionService.findQuestionById(1)).thenReturn(testQuestion);
        answerService.addAnswerToQuestion(getIncorrectAnswerWithIdDto(), 1);
        Set<AnswerDto> testAnswers = getQuestionDtoFullParam().getAnswers();
        testAnswers.add(getIncorrectAnswerWithIdDto());
        assertEquals(testAnswers, testQuestion.getAnswers());
        verify(answerRepository, times(1)).save(answerDtoConverter.fromDtoToEntity(getIncorrectAnswerWithIdDto()));
        verify(questionService, times(1)).findQuestionById(1);
    }

    @Test
    void updateAnswer() {
        answerService.updateAnswer(getIncorrectAnswerWithIdDto());
        verify(answerRepository, times(1)).save(answerDtoConverter.fromDtoToEntity(getIncorrectAnswerWithIdDto()));
    }

    @Test
    void findAnswerById() throws NotFoundException {
        when(answerRepository.findById(1)).thenReturn(Optional.of(answerDtoConverter.fromDtoToEntity(getCorrectAnswerDto())));
        AnswerDto testAnswer = answerService.findAnswerById(1);
        assertEquals(testAnswer, getCorrectAnswerDto());
        verify(answerRepository, times(1)).findById(1);
    }

    @Test
    void findAnswersByQuestionId() throws NotFoundException {
        when(questionService.findQuestionById(1)).thenReturn(getQuestionDtoFullParam());
        Set<AnswerDto> answerDtos = answerService.findAnswersByQuestionId(1);
        assertEquals(answerDtos, getAnswersDto());
        verify(questionService, times(1)).findQuestionById(1);
    }

    @Test
    void deleteAnswerById() {
        answerService.deleteAnswerById(1);
        verify(answerRepository, times(1)).deleteById(1);
    }
}