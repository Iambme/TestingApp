package com.quiz.service.impl;


import com.quiz.entities.Quiz;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.entities.dtos.QuizDto;
import com.quiz.exception.NotFoundException;
import com.quiz.repository.QuizRepository;
import com.quiz.service.interf.ConverterDto;
import com.quiz.service.interf.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final ConverterDto<Quiz, QuizDto> quizConverterDto;


    @Override
    public void addQuiz(QuizDto quizDto) {
        quizRepository.save(quizConverterDto.fromDtoToEntity(quizDto));
    }

    @Override
    public void addQuestionToQuizById(Integer quizId, QuestionDto questionDto) throws NotFoundException {
        QuizDto quizDto = findQuizById(quizId);
        Set<QuestionDto> questions = quizDto.getQuestions();
        questions.add(questionDto);
        quizDto.setQuestions(questions);
        updateQuiz(quizDto);
    }

    @Override
    public QuizDto findQuizById(Integer id) throws NotFoundException {
        return quizConverterDto.fromEntityToDto(quizRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quiz not found with id: " + id)));
    }

    @Override
    public List<QuizDto> findAll() {
        return quizRepository.findAll()
                .stream()
                .map(quizConverterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizDto> findQuizByUserId(Integer userId) {

        return quizRepository.findByUserId(userId).stream()
                .map(quizConverterDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateQuiz(QuizDto quizDto) {
        quizRepository.save(quizConverterDto.fromDtoToEntity(quizDto));

    }

    @Override
    public void deleteQuizById(Integer id) {
        quizRepository.deleteById(id);
    }

    @Override
    public void deleteQuizByUserId(Integer userId) {
        quizRepository.deleteQuizzesByUserId(userId);
    }


}
