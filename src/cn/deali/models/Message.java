package cn.deali.models;

import java.time.LocalTime;

public class Message {
    private User user;
    private String content;
    private LocalTime time;

    public Message(User user, String content, LocalTime time) {
        this.user = user;
        this.content = content;
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
