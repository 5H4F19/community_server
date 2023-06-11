package com.community.student.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.student.models.Answer;
import com.community.student.models.AnswersRepo;
import com.community.student.models.Question;
import com.community.student.models.QuestionRepo;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/a")
public class AnswerController {
    @Autowired
    AnswersRepo answersRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    UserController userCtrlr;
    @Autowired
    QuestionController questionCtrlr;

    public List<Answer> getAnswers(List<String> answerIds){
        return answersRepo.findAllById(answerIds);
    }

    @PostMapping("/{questionId}")
    public ResponseEntity<?> createAnswer(HttpServletRequest request, @PathVariable String questionId,@RequestBody Answer answer){
        Optional<Question> question = questionRepo.findById(questionId);
        String userId = request.getHeader("authorization");
        if(!question.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
        answer.setQuestionId(questionId);
        answer.setUser(userId);
        Answer answer2 = answersRepo.save(answer);
        questionCtrlr.updateQuestion(questionId, answer2.getId());
        return ResponseEntity.ok(answer2);
    }
}
