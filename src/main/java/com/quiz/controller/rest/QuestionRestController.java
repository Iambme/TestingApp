package com.quiz.controller.rest;


import com.quiz.entities.dtos.QuestionDto;
import com.quiz.exception.NotFoundException;
import com.quiz.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/question")
public class QuestionRestController {
    private final QuestionService questionService;


    @GetMapping("/all")
    public ResponseEntity<Set<QuestionDto>> getAll() {

        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(questionService.findQuestionById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveQuestion(@Validated @RequestBody QuestionDto questionDto) {
        questionService.addQuestion(questionDto);
        return ResponseEntity.ok("Question successfully added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateQuestion(@Validated @RequestBody QuestionDto questionDto) {
        questionService.updateQuestion(questionDto);
        return ResponseEntity.ok("Question successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable("id") Integer id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.ok("Question successfully deleted");
    }
}
