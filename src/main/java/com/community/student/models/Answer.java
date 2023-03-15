package com.community.student.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "answers")
public class Answer {
        @Id
        private String id;
        private String user;
        private String text;

        public Answer(String text, String user, String[] answers) {
            this.text = text;
            this.user = user;
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
    }