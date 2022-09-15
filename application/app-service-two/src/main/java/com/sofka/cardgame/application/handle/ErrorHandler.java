package com.sofka.cardgame.application.handle;

import com.sofka.cardgame.application.handle.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.servlet.function.ServerRequest;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Component
public class ErrorHandler {

    private static final BiFunction<HttpStatus,String, Mono<ServerResponse>> response =
            (status,value)-> ServerResponse.status(status).body(Mono.just(new ErrorResponse(value)),
                    ErrorResponse.class);

    Mono<ServerResponse> notFound(ServerRequest request){
        return response.apply(HttpStatus.NOT_FOUND, "not found");
    }

    Mono<ServerResponse> badRequest(Throwable error){
        error.printStackTrace();
        return response.apply(HttpStatus.BAD_REQUEST, error.getMessage());
    }
}
