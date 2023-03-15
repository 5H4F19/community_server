package com.community.student.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.community.student.models.Space;
import com.community.student.models.SpaceRepo;

@RestController
@RequestMapping("/s")
public class SpaceController {
    @Autowired
    private SpaceRepo spaceRepo;

    @GetMapping("")
    public List<Space> getSpaces(){
        return spaceRepo.findAll();
    }

    @PostMapping("/{spaceTitle}")
    public Space createSpace(@PathVariable String spaceTitle){
        List<String> questions = new ArrayList<String>();
        Space space = new Space(spaceTitle,questions);
        return spaceRepo.save(space);
    }

    @PutMapping("/{spaceId}")
    public ResponseEntity<?> updateSpace(@PathVariable String spaceId,@RequestBody String questionId ){
        // find space by id
        Optional<Space> space = spaceRepo.findById(spaceId);

        if(!space.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Space space2 = space.get();
        // get questions
        List<String> questions = space2.getQuestions();
        // add question
        questions.add(questionId);
        // update question
        space2.setQuestions(questions);
        // update space
        spaceRepo.save(space2);

        return ResponseEntity.ok().build();
    }
}
