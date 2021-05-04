package com.quiz.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quiz.service.interf.QuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.quiz.prototype.QuestionPrototype.getQuestionDtoFullParam;
import static com.quiz.prototype.QuizPrototype.getQuizDtoFullParam;
import static com.quiz.prototype.QuizPrototype.getQuizDtoList;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QuizRestControllerTest {
    private final QuizService quizService = mock(QuizService.class);
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(new QuizRestController(quizService)).build();
    }

    @Test
    void getAllQuiz() throws Exception {
        when(quizService.findAll()).thenReturn(getQuizDtoList());
        mockMvc.perform(get("/quiz/all").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuizDtoList())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getQuizDtoList())));
        verify(quizService, times(1)).findAll();
    }

    @Test
    void getQuiz() throws Exception {
        when(quizService.findQuizById(1)).thenReturn(getQuizDtoFullParam());
        mockMvc.perform(get("/quiz/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuizDtoFullParam())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getQuizDtoFullParam())));
        verify(quizService, times(1)).findQuizById(1);
    }

    @Test
    void getAllByUser() throws Exception {
        when(quizService.findQuizByUserId(1)).thenReturn(getQuizDtoList());
        mockMvc.perform(get("/quiz/allByUser/{id}", 1).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuizDtoList())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getQuizDtoList())));
        verify(quizService, times(1)).findQuizByUserId(1);
    }

    @Test
    void saveNewQuiz() throws Exception {
        mockMvc.perform(post("/quiz/save")
                .param("quizDto", objectMapper.writeValueAsString(getQuizDtoFullParam()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuizDtoFullParam())))
                .andExpect(status().isOk());
        verify(quizService, times(1)).addQuiz(getQuizDtoFullParam());
    }

    @Test
    void addQuestionToQuiz() throws Exception {
        when(quizService.findQuizById(1)).thenReturn(getQuizDtoFullParam());
        mockMvc.perform(post("/quiz/addQuestion/{id}", 1)
                .param("questionDto", objectMapper.writeValueAsString(getQuestionDtoFullParam()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuestionDtoFullParam())))
                .andExpect(status().isOk());
        verify(quizService, times(1)).addQuestionToQuizById(1, getQuestionDtoFullParam());
    }

    @Test
    void updateQuiz() throws Exception {
        mockMvc.perform(put("/quiz/update")
                .param("quizDto", objectMapper.writeValueAsString(getQuizDtoFullParam()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(getQuizDtoFullParam())))
                .andExpect(status().isOk());
        verify(quizService, times(1)).updateQuiz(getQuizDtoFullParam());
    }

    @Test
    void deleteQuiz() throws Exception {
        mockMvc.perform(delete("/quiz/delete/{id}", 1))
                .andExpect(status().isOk());
        verify(quizService, times(1)).deleteQuizById(1);
    }
}