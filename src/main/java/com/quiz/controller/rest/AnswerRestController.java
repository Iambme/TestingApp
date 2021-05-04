package com.quiz.controller.rest;


import com.quiz.entities.dtos.AnswerDto;
import com.quiz.exception.NotFoundException;
import com.quiz.service.interf.AnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/answer")
public class AnswerRestController {
    private final AnswerService answerService;

    @GetMapping("/allByQuestion/{id}")
    public ResponseEntity<Set<AnswerDto>> getAllByQuestion(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(answerService.findAnswersByQuestionId(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDto> getAnswer(@PathVariable Integer id) throws NotFoundException {
        return ResponseEntity.ok(answerService.findAnswerById(id));
    }

    @PostMapping("/save/{questionId}")
    public ResponseEntity<String> saveAnswer(@Validated @RequestBody AnswerDto answerDto, @PathVariable Integer questionId) throws NotFoundException {
        answerService.addAnswerToQuestion(answerDto, questionId);
        return ResponseEntity.ok("Answer successfully added");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAnswer(@Validated @RequestBody AnswerDto answerDto) {
        answerService.updateAnswer(answerDto);
        return ResponseEntity.ok("Answer successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnswer(@PathVariable("id") Integer id) {
        answerService.deleteAnswerById(id);
        return ResponseEntity.ok("Answer successfully deleted");
    }
}
