package com.quiz.controller.simple;


import com.quiz.service.interf.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class SimpleController {
    private final QuestionService questionService;

//    @GetMapping("/")
//    public String startPage(Model model) throws NotFoundException {
//        QuestionDto questionDto = questionService.findQuestionById(2);
//        model.addAttribute("question", questionDto);
//        return "index";
//    }
}
