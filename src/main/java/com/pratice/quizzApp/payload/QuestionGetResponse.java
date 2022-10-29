package com.pratice.quizzApp.payload;

import com.pratice.quizzApp.models.Option;
import com.pratice.quizzApp.models.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionGetResponse {

    private List<Question> questions;
    private String message;
}
