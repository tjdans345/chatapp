package com.meteor.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.awt.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController // 데이터 리턴 서버
public class ChatController {

    private final ChatRepository chatRepository;

    // 귓속말 할 때 쓰자!!!!!
    // MediaType.TEXT_EVENT_STREAM_VALUE (SSE 프로토콜 한방에 응답하는것이 아니라 데이터가 생길 때 마다 지속적으로 계속 데이터 응답해줌)
    @CrossOrigin
    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver) {
        return chatRepository.mFindBySender(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @GetMapping(value = "/chat/roomNum/{roomNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByRoomNum(@PathVariable Integer roomNum) {
        return chatRepository.mFindByRoomNum(roomNum)
                .subscribeOn(Schedulers.boundedElastic());
    }

    // Flux : 데이터를 계속 리턴
    // Mono : 데이터를 한번만 리턴
    @CrossOrigin
    @PostMapping("/chat")
    public Mono<Chat> setMsg(@RequestBody Chat chat) {
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat); // 스프링은 Object를 리턴하면 자동으로 JSON으로 변환 시켜준다(feat.MessageConverter)
    }



}
