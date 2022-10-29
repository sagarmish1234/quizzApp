package com.pratice.quizzApp.service;

import com.pratice.quizzApp.models.Answer;
import com.pratice.quizzApp.models.Option;
import com.pratice.quizzApp.models.Question;
import com.pratice.quizzApp.models.User;
import com.pratice.quizzApp.payload.AnswerRequest;
import com.pratice.quizzApp.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    OptionService optionService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    public boolean saveAnswer(AnswerRequest request) throws Exception{
        try{
            Question question = questionService.loadQuestionById(request.getQuestionId());
            User user = userService.getUserFromPrincipal();
            Option option = optionService.loadOptionByBy(request.getOptionId());
            Answer ans = new Answer(null,question,user,option,option.getBody().equals(question.getCorrectOption().getBody()));
            ans = answerRepository.save(ans);
            return ans.isCorrect();
        } catch (Exception e) {
            throw e;
        }
    }

}
