package com.pratice.quizzApp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
    private boolean isCorrect;
    private String message;
}
