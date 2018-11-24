package com.moommim.moommim_web.model;

public class MailMessage {

    private String subject;
    private String content;

    public MailMessage() {
    }

    public MailMessage(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MailMessage{" + "subject=" + subject + ", content=" + content + '}';
    }

}
