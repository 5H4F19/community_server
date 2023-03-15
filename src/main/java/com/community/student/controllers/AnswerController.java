package com.community.student.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.student.models.Answer;
import com.community.student.models.AnswersRepo;

@RestController
@RequestMapping("/a")
public class AnswerController {

    @Autowired
    private AnswersRepo quesRepo;
    @Autowired
    private QuestionController questionCtrlr;
    
    @PostMapping("/{questionId}")
    public Answer createAnswer(@PathVariable String questionId, @RequestBody Answer answer) {
        // create a answer and save
        Answer ans = quesRepo.save(answer);
        // get the id of that answer
        String answerId = ans.getId();
        // update the question by the answer
        questionCtrlr.updateQuestion(questionId, answerId);
        return ans;
    }

}
