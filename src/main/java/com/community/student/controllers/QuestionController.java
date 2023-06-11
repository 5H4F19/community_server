package com.community.student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.community.student.models.Answer;
import com.community.student.models.AnswersRepo;
import com.community.student.models.Question;
import com.community.student.models.QuestionRepo;
import com.community.student.models.UserRepo;

import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/q")

public class QuestionController {
    @Autowired
    private QuestionRepo quesRepo;
    @Autowired
    private AnswersRepo answersRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SpaceController spaceCtrlr;


   @GetMapping("")
public List<Question> getAllQuestions() {
    List<Question> questions = quesRepo.findAll();
    questions.sort(Comparator.comparing(Question::getId).reversed());

    List<Question> questionsWithAnswers = new ArrayList<>();

    for (Question question : questions) {
        List<String> answerIds = question.getAnswersIds();
        List<Answer> answers = answersRepo.findAllById(answerIds);
        answers.sort(Comparator.comparing(Answer::getId).reversed());
        question.setAnswers(answers);

        if (!answers.isEmpty()) {
            questionsWithAnswers.add(question);
        }
    }
    return questionsWithAnswers;
}
  @GetMapping("/latest")
public List<Question> getAllQuestionsToAnswer() {
    List<Question> questions = quesRepo.findAll();

    List<Question> questionsWithAnswers = new ArrayList<>();

    for (Question question : questions) {
        List<String> answerIds = question.getAnswersIds();
        List<Answer> answers = answersRepo.findAllById(answerIds);
        answers.sort(Comparator.comparing(Answer::getId).reversed());
        question.setAnswers(answers);

        if (answers.isEmpty() || answers.size()<2) {
            questionsWithAnswers.add(question);
        }
    }
    return questionsWithAnswers;
}

    @GetMapping("/{questionId}")
    public ResponseEntity<?> getAQuestions(@PathVariable String questionId) {
       Optional<Question> question = quesRepo.findById(questionId);
      if(!question.isPresent()) {
        return ResponseEntity.notFound().build();
      }
      Question question2 = question.get();
      List<Answer> answers = answersRepo.findAllById(question2.getAnswersIds());
      answers.sort(Comparator.comparing(Answer::getId).reversed());
      question2.setAnswers(answers);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(question2);
    }

    @PostMapping("/{spaceId}")
    public Question createQuestion(HttpServletRequest request, @PathVariable String spaceId, @RequestBody Question question) {
        String userId = request.getHeader("authorization");
        List<Answer> blankAnswers = new ArrayList<>();
        question.setAnswers(blankAnswers);
        question.setUser(userId);
        Question ques = quesRepo.save(question);
        String questionId = ques.getId();
        spaceCtrlr.updateSpace(spaceId,questionId);
        return ques;
    }

    public ResponseEntity<?> updateQuestion(String questionId,String answerId){
      Optional<Question> question = quesRepo.findById(questionId);
      if(!question.isPresent()) {
        return ResponseEntity.notFound().build();
      }
      Question question2 = question.get();
      List<String> answerIds = question2.getAnswersIds();
      answerIds.add(answerId);
      quesRepo.save(question2);

      return ResponseEntity.ok(question2);
    }
}
