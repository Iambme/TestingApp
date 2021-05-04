package com.quiz.service.impl;

import com.quiz.entities.Question;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.exception.NotFoundException;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.interf.ConverterDto;
import com.quiz.service.interf.QuestionService;
import com.quiz.util.AnswerDtoConverter;
import com.quiz.util.QuestionDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.quiz.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static com.quiz.prototype.QuestionPrototype.getQuestionsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuestionServiceImplTest {
    private final QuestionRepository questionRepository = mock(QuestionRepository.class);
    private ConverterDto<Question, QuestionDto> questionConverterDto;
    private QuestionService questionService;

    @BeforeEach
    public void setup() {
        questionConverterDto = new QuestionDtoConverter(new AnswerDtoConverter());
        questionService = new QuestionServiceImpl(questionRepository, questionConverterDto);
    }


    @Test
    void addQuestion() {
        QuestionDto questionDto = getQuestionDtoFullParam();
        questionService.addQuestion(questionDto);
        verify(questionRepository, times(1)).save(questionConverterDto.fromDtoToEntity(questionDto));
    }

    @Test
    void updateQuestion() {
        questionService.updateQuestion(getQuestionDtoFullParam());
        verify(questionRepository, times(1)).save(questionConverterDto.fromDtoToEntity(getQuestionDtoFullParam()));
    }

    @Test
    void findQuestionById() throws NotFoundException {
        when(questionRepository.findById(1)).thenReturn(Optional.of(questionConverterDto.fromDtoToEntity(getQuestionDtoFullParam())));
        QuestionDto questionDto = questionService.findQuestionById(1);
        assertEquals(questionDto, getQuestionDtoFullParam());
        verify(questionRepository, times(1)).findById(1);
    }

    @Test
    void findAll() {
        when(questionRepository.findAll()).thenReturn(getQuestionsDto().stream()
                .map(x -> questionConverterDto.fromDtoToEntity(x)).collect(Collectors.toList()));
        Set<QuestionDto> questionDtoList = questionService.findAll();
        assertEquals(questionDtoList, getQuestionsDto());
        verify(questionRepository, times(1)).findAll();
    }

    @Test
    void deleteQuestionById() {
        questionService.deleteQuestionById(1);
        verify(questionRepository, times(1)).deleteById(1);
    }
}