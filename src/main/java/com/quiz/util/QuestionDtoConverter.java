package com.quiz.util;


import com.quiz.entities.Answer;
import com.quiz.entities.Question;
import com.quiz.entities.dtos.AnswerDto;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.service.interf.ConverterDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuestionDtoConverter implements ConverterDto<Question, QuestionDto> {
    public final ConverterDto<Answer, AnswerDto> answerDtoConverter;

    @Override
    public Question fromDtoToEntity(QuestionDto dto) {
        return Question.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .image(dto.getImage())
                .answers(dto.getAnswers()
                        .stream()
                        .map(answerDtoConverter::fromDtoToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public QuestionDto fromEntityToDto(Question entity) {
        return QuestionDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .image(entity.getImage())
                .answers(entity.getAnswers()
                        .stream()
                        .map(answerDtoConverter::fromEntityToDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
