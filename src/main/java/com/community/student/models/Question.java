package com.community.student.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questions")
public class Question {
        @Id
        private String id;
        private String user;
        private String text;
        private List<Answer> answers;
        private List<String> answersIds;
        @CreatedDate
        private Date createdAt;
        @LastModifiedDate
        private Date updatedAt;

        public Question(String text, String user) {
            this.text = text;
            this.user = user;
            this.answers = new ArrayList<>();
            this.answersIds = new ArrayList<>();
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
        public List<String> getAnswersIds() {
            return answersIds;
        }
        public void setAnswersIds(List<String> answersIds) {
            this.answersIds = answersIds;
        }
        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }

        public List<Answer> getAnswers() {
            return answers;
        }
        public void setAnswers(List<Answer> answers) {
            this.answers = answers;
        }
        public Date getCreatedAt() {
            return createdAt;
        }
        public Date getUpdatedAt() {
            return updatedAt;
        }
    }