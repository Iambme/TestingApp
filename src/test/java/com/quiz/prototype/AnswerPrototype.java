package com.quiz.prototype;


import com.quiz.entities.dtos.AnswerDto;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class AnswerPrototype {
    public static AnswerDto getCorrectAnswerDto() {
        return AnswerDto.builder()
                .id(2)
                .text("test_text_true_answer")
                .correct(true)
                .build();
    }
    public static AnswerDto getIncorrectAnswerWithIdDto() {
        return AnswerDto.builder()
                .id(1)
                .text("test_text_false_answer")
                .correct(false)
                .build();
    }

    public static Set<AnswerDto> getAnswersDto() {
        return new HashSet<>() {{
            add(getCorrectAnswerDto());
            add(AnswerDto.builder()
                    .text("test_another_answer_text")
                    .correct(false)
                    .build());
        }};
    }
}
