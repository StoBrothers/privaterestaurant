package com.privaterestaurant.util;

public class Message {

    private String kind = null;
    private String title = null;
    private String shortMessage = null;
    private String message = null;

    public Message(Kind kind, String message) {
        super();
        this.kind = kind.name();
        this.message = message;
    }

    public Message(Kind kind, String title, String message, String shortMessage) {
        this(kind, message);
        this.title = title;
        this.shortMessage = shortMessage;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortMessage() {
        return shortMessage;
    }

    public void setShortMessage(String shortMessage) {
        this.shortMessage = shortMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public enum Kind {
        success, error, info, warn;
    }
}
