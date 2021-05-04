package com.quiz.util;


import com.quiz.entities.Answer;
import com.quiz.entities.dtos.AnswerDto;
import com.quiz.service.interf.ConverterDto;
import org.springframework.stereotype.Component;

@Component
public class AnswerDtoConverter implements ConverterDto<Answer, AnswerDto> {
    @Override
    public Answer fromDtoToEntity(AnswerDto dto) {
        return Answer.builder()
                .id(dto.getId())
                .text(dto.getText())
                .correct(dto.isCorrect())
                .build();
    }

    @Override
    public AnswerDto fromEntityToDto(Answer entity) {
        return AnswerDto.builder()
                .id(entity.getId())
                .text(entity.getText())
                .correct(entity.isCorrect())
                .build();
    }
}
