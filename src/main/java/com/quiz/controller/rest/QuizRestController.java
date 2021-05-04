package com.quiz.controller.rest;


import com.quiz.entities.dtos.QuestionDto;
import com.quiz.entities.dtos.QuizDto;
import com.quiz.exception.NotFoundException;
import com.quiz.service.interf.QuizService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/quiz")
public class QuizRestController {
    private final QuizService quizService;



    @GetMapping("/all")
    public ResponseEntity<List<QuizDto>> getAllQuiz() {
        return ResponseEntity.ok(quizService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizDto> getQuiz(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(quizService.findQuizById(id));
    }

    @GetMapping("/allByUser/{id}")
    public ResponseEntity<List<QuizDto>> getAllQuizByUser(@PathVariable Integer id) {
        return ResponseEntity.ok(quizService.findQuizByUserId(id));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveNewQuiz(@Validated @RequestBody QuizDto quizDto) {

        quizService.addQuiz(quizDto);
        return ResponseEntity.ok("Quiz successfully added");
    }

    @PostMapping("/addQuestion/{id}")
    public ResponseEntity<String> addQuestionToQuiz(@PathVariable Integer id, @Validated @RequestBody QuestionDto questionDto) throws NotFoundException {
        quizService.addQuestionToQuizById(id, questionDto);
        return ResponseEntity.ok("Question successfully added to quiz with id: " + id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateQuiz(@Validated @RequestBody QuizDto quizDto) {
        quizService.updateQuiz(quizDto);
        return ResponseEntity.ok("Quiz successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable("id") Integer id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.ok("Quiz successfully deleted");
    }
}
