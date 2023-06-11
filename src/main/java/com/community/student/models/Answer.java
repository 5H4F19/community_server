package com.community.student.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answers")
public class Answer {
        @Id
        private String id;
        private String user;
        private String text;
        private String questionId;

        @CreatedDate
        private Date createdAt;
        @LastModifiedDate
        private Date updatedAt;

        public Answer(String text, String user,String questionId) {
            this.text = text;
            this.user = user;
            this.questionId = questionId;
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
        public String getQuestionId() {
            return questionId;
        }
        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getText() {
            return text;
        }
        public void setText(String text) {
            this.text = text;
        }
        public Date getCreatedAt() {
            return createdAt;
        }
        public Date getUpdatedAt() {
            return updatedAt;
        }
    }