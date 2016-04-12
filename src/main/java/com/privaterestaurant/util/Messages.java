package com.privaterestaurant.util;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.privaterestaurant.util.Message.Kind;

/**
 * 
 */
public class Messages {
    private List<Message> messages;

    private Messages() {
        this(1);
    }

    private Messages(int capacity) {
        messages = new ArrayList<>(capacity);
    }

    public List<Message> build() {
        return messages;
    }

    public void build(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("messages", messages);
    }

    public Messages addMessage(Message message) {
        messages.add(message);
        return this;
    }

    public Messages addMessages(List<Message> messages) {
        this.messages.addAll(messages);
        return this;
    }

    public Messages addMessage(Kind kind, String message) {
        return addMessage(new Message(kind, message));
    }

    public Messages error(String message) {
        return addMessage(Kind.error, message);
    }

    public Messages success(String message) {
        return addMessage(Kind.success, message);
    }

    public Messages addMessages(Kind kind, String... messages) {
        return addMessages(Arrays.asList(messages).stream()
            .map(message -> new Message(kind, message)).collect(toList()));
    }

    public static Messages create() {
        return new Messages();
    }

    public static Messages create(int capacity) {
        return new Messages(capacity);
    }

}
