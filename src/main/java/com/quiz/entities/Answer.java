package com.quiz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "answers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "text")
    private String text;
    @Column(name = "is_correct")
    private boolean correct;
    //TODO:create relationship
    @ManyToOne
    private Question question;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return correct == answer.correct && text.equals(answer.text) && question.equals(answer.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, correct, question);
    }
}
