package com.community.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.community.student.models.Question;
import com.community.student.models.QuestionRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/q")
public class QuestionController {

    @Autowired
    private QuestionRepo quesRepo;
    @Autowired
    private SpaceController spaceCtrlr;

    @GetMapping("")
    public List<Question> getAllQuestions() {
        return quesRepo.findAll();
    }

    @PostMapping("/{spaceId}")
    public Question createQuestion(@PathVariable String spaceId, @RequestBody Question question) {
        Question ques = quesRepo.save(question);
        String questionId = ques.getId();
        spaceCtrlr.updateSpace(spaceId,questionId);
        return ques;
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable String questionId, @RequestBody String answerId){
        // find question by id
      Optional<Question> ques = quesRepo.findById(questionId);
      // return error if not found
      if(!ques.isPresent()){
        return ResponseEntity.notFound().build();
      }
      // retrive question
      Question question = ques.get();
      // update the answers
      List<String> answers = question.getAnswers();
      answers.add(answerId);
      // add answer to the question
      question.setAnswers(answers);
      // update the question
      quesRepo.save(question);

      return ResponseEntity.ok().build();
    }
}
