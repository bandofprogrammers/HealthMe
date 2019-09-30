package com.healthme.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long senderId;

    @NotBlank
    private String senderClass;

    @NotNull
    private Long receiverId;

    @NotNull
    private String receiverClass;

    @NotBlank
    private String topic;

    @NotBlank
    private String content;

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderClass() {
        return senderClass;
    }

    public void setSenderClass(String senderClass) {
        this.senderClass = senderClass;
    }

    public String getReceiverClass() {
        return receiverClass;
    }

    public void setReceiverClass(String receiverClass) {
        this.receiverClass = receiverClass;
    }
}
