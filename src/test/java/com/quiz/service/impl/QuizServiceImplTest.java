package com.quiz.service.impl;

import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.entities.dtos.QuizDto;
import com.quiz.exception.NotFoundException;
import com.quiz.repository.QuizRepository;
import com.quiz.service.interf.ConverterDto;
import com.quiz.service.interf.QuizService;
import com.quiz.util.AnswerDtoConverter;
import com.quiz.util.QuestionDtoConverter;
import com.quiz.util.QuizDtoConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.quiz.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static com.quiz.prototype.QuizPrototype.getQuizDtoFullParam;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class QuizServiceImplTest {
    private final QuizRepository quizRepository = mock(QuizRepository.class);
    private ConverterDto<Quiz, QuizDto> quizDtoConverter;
    private ConverterDto<Question, QuestionDto> questionDtoConverter;
    private QuizService quizService;
    List<QuizDto> quizzes;

    @BeforeEach
    void setup() {
        questionDtoConverter = new QuestionDtoConverter(new AnswerDtoConverter());
        quizDtoConverter = new QuizDtoConverter(questionDtoConverter);
        quizService = new QuizServiceImpl(quizRepository, quizDtoConverter);
        quizzes = new ArrayList<>();
        quizzes.add(getQuizDtoFullParam());
    }

    @Test
    void addQuiz() {
        QuizDto quizDto = getQuizDtoFullParam();
        quizService.addQuiz(quizDto);
        verify(quizRepository, times(1)).save(quizDtoConverter.fromDtoToEntity(quizDto));
    }

    @Test
    void addQuestionToQuizById() throws NotFoundException {
        Quiz quiz = quizDtoConverter.fromDtoToEntity(getQuizDtoFullParam());
        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
        QuestionDto questionDto = getQuestionDtoFullParam();
        quizService.addQuestionToQuizById(1, questionDto);
        verify(quizRepository, times(1)).findById(1);
        verify(quizRepository, times(1)).save(quiz);
    }

    @Test
    void findQuizById() throws NotFoundException {
        Quiz quiz = quizDtoConverter.fromDtoToEntity(getQuizDtoFullParam());
        when(quizRepository.findById(1)).thenReturn(Optional.of(quiz));
        QuizDto quizDto = quizService.findQuizById(1);
        assertEquals(quizDto, getQuizDtoFullParam());
        verify(quizRepository, times(1)).findById(1);
    }

    @Test
    void findAll() {

        when(quizRepository.findAll()).thenReturn(quizzes.stream()
                .map(quizDto -> quizDtoConverter.fromDtoToEntity(quizDto)).collect(Collectors.toList()));
        List<QuizDto> quizDtoList = quizService.findAll();
        assertEquals(quizDtoList, quizzes);
        verify(quizRepository, times(1)).findAll();
    }

    @Test
    void findQuizByUserId() {
        when(quizRepository.findByUserId(1)).thenReturn(quizzes.stream()
                .map(quizDto -> quizDtoConverter.fromDtoToEntity(quizDto)).collect(Collectors.toList()));
        List<QuizDto> quizDtoList = quizService.findQuizByUserId(1);
        assertEquals(quizDtoList, quizzes);
        verify(quizRepository, times(1)).findByUserId(1);
    }

    @Test
    void updateQuiz() {
        quizService.updateQuiz(getQuizDtoFullParam());
        verify(quizRepository, times(1)).save(quizDtoConverter.fromDtoToEntity(getQuizDtoFullParam()));
    }

    @Test
    void deleteQuizById() {
        quizService.deleteQuizById(1);
        verify(quizRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteQuizByUserId() {
        quizService.deleteQuizByUserId(1);
        verify(quizRepository, times(1)).deleteQuizzesByUserId(1);
    }
}