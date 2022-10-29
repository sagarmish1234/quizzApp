package com.pratice.quizzApp.service;

import com.pratice.quizzApp.models.Option;
import com.pratice.quizzApp.models.Question;
import com.pratice.quizzApp.models.User;
import com.pratice.quizzApp.payload.QuestionCreateRequest;
import com.pratice.quizzApp.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OptionService optionService;

    public Question loadQuestionById(Long id) throws Exception {
        Question question = questionsRepository.findById(id).orElseThrow(() -> new Exception("No question found"));
        return question;
    }

    public void saveQuestion(QuestionCreateRequest request) {
        User user = userService.getUserFromPrincipal();
        Set<Option> options = (Set<Option>) request.getOptions().stream().map(opt -> new Option(opt)).collect(Collectors.toSet());
        options = new HashSet<>(optionService.saveAllOptions(options));
        Option correctOption = options.stream().filter(e -> e.checkStringEquality(request.getCorrectAnswer())).collect(Collectors.toList()).get(0);
        Question question = new Question(null, request.getQuestion(), options, correctOption, user);
        questionsRepository.save(question);
    }

    public List<Question> loadAllQuestions() {
        return questionsRepository.findAll();
    }

}
