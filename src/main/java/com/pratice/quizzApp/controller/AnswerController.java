package com.pratice.quizzApp.controller;

import com.pratice.quizzApp.payload.AnswerRequest;
import com.pratice.quizzApp.payload.AnswerResponse;
import com.pratice.quizzApp.payload.ApiResponse;
import com.pratice.quizzApp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/give")
    public ResponseEntity<?> giveAnswer(@Valid @RequestBody AnswerRequest answerRequest) {
        try {
            boolean answer = answerService.saveAnswer(answerRequest);
            return ResponseEntity.ok(new AnswerResponse(answer, answer ? "Correct Option" : "Incorrect Option"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
        }
    }
}
