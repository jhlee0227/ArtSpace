package com.example.demo.email;

public class EmailMessage {

    private String to;
    private String subject;
    private Object message;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public static class EmailMessageBuilder {
        private String to;
        private String subject;
        private String message;

        public EmailMessageBuilder to(String to) {
            this.to = to;
            return this;
        }

        public EmailMessageBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailMessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public EmailMessage build() {
            EmailMessage emailMessage = new EmailMessage();
            emailMessage.setTo(this.to);
            emailMessage.setSubject(this.subject);
            emailMessage.setMessage(this.message);
            return emailMessage;
        }
    }
}
