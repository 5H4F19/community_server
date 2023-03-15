package com.community.student.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "space")
public class Space {
    @Id
    private String id;
    private String title;
    private List<String> questions;
    public Space(String title, List<String> questions){
        this.title = title;
        this.questions = questions;
    }
    public String getId() {
        return id;
    }
    public List<String> getQuestions() {
        return questions;
    }
    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
