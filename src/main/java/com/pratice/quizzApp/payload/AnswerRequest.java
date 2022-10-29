package com.pratice.quizzApp.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {

    @NotNull
    private Long questionId;

    @NotNull
    private Long optionId;
}
