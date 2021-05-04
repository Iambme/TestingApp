package com.quiz.util;


import com.quiz.entities.Question;
import com.quiz.entities.Quiz;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.entities.dtos.QuizDto;
import com.quiz.service.interf.ConverterDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuizDtoConverter implements ConverterDto<Quiz, QuizDto> {
    private final ConverterDto<Question, QuestionDto> questionConverterDto;

    @Override
    public Quiz fromDtoToEntity(QuizDto dto) {
        return Quiz.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .subject(dto.getSubject())
                .userId(dto.getUserId())
                .questions(dto.getQuestions()
                        .stream()
                        .map(questionConverterDto::fromDtoToEntity)
                        .collect(Collectors.toSet()))
                .result(dto.getDescription())
                .build();
    }

    @Override
    public QuizDto fromEntityToDto(Quiz entity) {
        return QuizDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .subject(entity.getSubject())
                .userId(entity.getUserId())
                .questions(entity.getQuestions()
                        .stream()
                        .map(questionConverterDto::fromEntityToDto)
                        .collect(Collectors.toSet()))
                .description(entity.getResult())
                .build();
    }
}
