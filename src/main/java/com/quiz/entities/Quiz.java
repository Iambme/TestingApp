package com.quiz.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;



@Data
@Builder
@Entity
@Table(name = "quizzes")
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String title;
    @Column(name = "subject")
    private String subject;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.REFRESH)
    private Set<Question> questions;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "result")
    private String result;

}
