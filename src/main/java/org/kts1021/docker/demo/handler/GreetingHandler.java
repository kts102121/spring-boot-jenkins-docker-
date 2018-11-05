package org.kts1021.docker.demo.handler;

import org.kts1021.docker.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {
    private final UserMapper userMapper;

    public GreetingHandler(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    private static final Logger log = LoggerFactory.getLogger(GreetingHandler.class);

    public Mono<ServerResponse> hello(ServerRequest request) {
        log.info("User: {}", userMapper.findByUsername("ksh10211").getUsername());

        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject(new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (System.currentTimeMillis())) + " / from: " + request.remoteAddress()));
    }
}
