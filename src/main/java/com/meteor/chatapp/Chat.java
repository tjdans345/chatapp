package com.meteor.chatapp;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "chat")
public class Chat {

    private String id;
    private String msg;
    private String sender; // 보내는 사람
    private String receiver; // 받는 사람람

    private LocalDateTime createdAt;

}