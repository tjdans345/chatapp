package com.meteor.chatapp;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface ChatRepository extends ReactiveMongoRepository<Chat, String> {

    // FLux (흐름) : 데이터를 한번 받고 끝내는게 아니라 계속 흘려서 받겠다
    //              response를 유지하면서 데이터를 계속 흘려보낸다.
    // SSE 프로토콜을 사용해야한다. (Http 프로토콜은 데이터를 쌓아뒀다가 한번에 응답함. 그리고 응답이 완료되면 연결을 끊어버림)
    @Tailable // 커서를 안닫고 계속 유지한다. => 관련된 데이터가 한건이라도 insert되면 계속 플럭스를 통해 흘러들어온다. (컨트롤러는 FLux를 통해 응답한다.)
    @Query("{sender:?0, receiver:?1")
    Flux<Chat> mfindBySender(String sender, String receiver);


}
