package com.quiz.prototype;


import com.quiz.entities.dtos.QuestionDto;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

import static com.quiz.prototype.AnswerPrototype.getAnswersDto;


@UtilityClass
public class QuestionPrototype {
    public static QuestionDto getQuestionDtoFullParam() {
        return QuestionDto.builder()
                .id(1)
                .title("test_title")
                .description("test_description")
                .image("test_image")
                .answers(getAnswersDto())
                .build();
    }

    public static Set<QuestionDto> getQuestionsDto() {
        return new HashSet<>() {{
            add(getQuestionDtoFullParam());
            add(QuestionDto.builder()
                    .id(2)
                    .title("test_title1")
                    .description("test_description2")
                    .answers(getAnswersDto())
                    .build());
        }};
    }
}
