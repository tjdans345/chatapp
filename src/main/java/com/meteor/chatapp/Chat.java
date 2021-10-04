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
    private String receiver; // 받는 사람 (귓속말 할 때 적절한 듯)
    private Integer roomNum; // 방 번호

    private LocalDateTime createdAt;

}