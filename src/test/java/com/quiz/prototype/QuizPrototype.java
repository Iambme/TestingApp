package com.quiz.prototype;


import com.quiz.entities.dtos.QuizDto;

import java.util.ArrayList;
import java.util.List;

import static com.quiz.prototype.QuestionPrototype.getQuestionsDto;


public class QuizPrototype {
    public static QuizDto getQuizDtoFullParam() {
        return QuizDto.builder()
                .id(1)
                .title("test_title")
                .description("test_description")
                .subject("test_subject")
                .questions(getQuestionsDto())
                .userId(1)
                .build();
    }

    public static List<QuizDto> getQuizDtoList() {
        return new ArrayList<>() {{
            add(getQuizDtoFullParam());
            add(QuizDto.builder()
                    .id(2)
                    .title("test_title_2")
                    .description("test_description_2")
                    .subject("test_subject_2")
                    .questions(getQuestionsDto())
                    .userId(1)
                    .build());
        }};
    }


}
