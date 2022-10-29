package com.pratice.quizzApp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreateRequest {

    @NotBlank
    @Size(max = 100)
    private String question;

    @Size(max = 6)
    private ArrayList<String> options;
    @NotBlank
    @Size(max = 30)
    private String correctAnswer;
}
