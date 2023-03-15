package com.community.student.models;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questions")
public class Question {
        @Id
        private String id;
        private String user;
        private String text;
        private List<String> answers;

        public Question(String text, String user, List<String> answers) {
            this.text = text;
            this.user = user;
            this.answers = answers;
        }
        public String getId(){
            return id;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }

        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }

        public List<String> getAnswers() {
            return answers;
        }
        public void setAnswers(List<String> answers) {
            this.answers = answers;
        }
    }