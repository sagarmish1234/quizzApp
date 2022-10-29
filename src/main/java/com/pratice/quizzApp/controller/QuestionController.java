package com.pratice.quizzApp.controller;

import com.pratice.quizzApp.models.Question;
import com.pratice.quizzApp.payload.ApiResponse;
import com.pratice.quizzApp.payload.QuestionCreateRequest;
import com.pratice.quizzApp.payload.QuestionGetResponse;
import com.pratice.quizzApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionCreateRequest request) {
        questionService.saveQuestion(request);
        return ResponseEntity.ok(new ApiResponse(true, "Question created successfully"));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllQuestions(){
        try{
            ArrayList<Question> questions = (ArrayList<Question>) questionService.loadAllQuestions();
            return ResponseEntity.ok(questions);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(new ApiResponse(false,"No questions found"));
        }
    }



}
