package com.quiz.service.impl;


import com.quiz.entities.Question;
import com.quiz.entities.dtos.QuestionDto;
import com.quiz.exception.NotFoundException;
import com.quiz.repository.QuestionRepository;
import com.quiz.service.interf.ConverterDto;
import com.quiz.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ConverterDto<Question, QuestionDto> questionConverterDto;

    @Override
    public void addQuestion(QuestionDto questionDto) {
        questionRepository.save(questionConverterDto.fromDtoToEntity(questionDto));
    }

    @Override
    public void updateQuestion(QuestionDto questionDto) {
        questionRepository.save(questionConverterDto.fromDtoToEntity(questionDto));
    }

    @Override
    public QuestionDto findQuestionById(Integer id) throws NotFoundException {
        return questionConverterDto.fromEntityToDto(questionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Question not found with id: " + id)));
    }

    @Override
    public Set<QuestionDto> findAll() {
        return questionRepository.findAll()
                .stream()
                .map(questionConverterDto::fromEntityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteQuestionById(Integer id) {
        questionRepository.deleteById(id);
    }


}
