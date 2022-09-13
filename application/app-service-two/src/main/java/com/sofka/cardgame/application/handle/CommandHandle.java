package com.sofka.cardgame.application.handle;

import com.sofka.cardgame.commands.CrearJuegoCommand;
import com.sofka.cardgame.commands.IniciarJuegoCommand;
import com.sofka.cardgame.commands.IniciarRondaCommand;
import com.sofka.cardgame.commands.PonerCartaEnTableroCommand;
import com.sofka.cardgame.usecases.CrearJuegoUseCase;
import com.sofka.cardgame.usecases.IniciarJuegoUseCase;
import com.sofka.cardgame.usecases.IniciarRondaUseCase;
import com.sofka.cardgame.usecases.PonerCartaEnTableroUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class CommandHandle {

    private final IntegrationHandle integrationHandle;

    public CommandHandle(IntegrationHandle integrationHandle) {
        this.integrationHandle = integrationHandle;
    }

    @Bean
    public RouterFunction<ServerResponse> crear(CrearJuegoUseCase usecase) {

        return route(
                POST("/juego/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> {
                    System.out.println(request);
                    return usecase.andThen(integrationHandle)
                            .apply(request.bodyToMono(CrearJuegoCommand.class))
                            .then(ServerResponse.ok().build());
                }

        );
    }


    @Bean
    public RouterFunction<ServerResponse> iniciar(IniciarJuegoUseCase usecase) {
        return route(
                POST("/juego/iniciar").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(IniciarJuegoCommand.class))
                        .then(ServerResponse.ok().build())

        );
    }

    @Bean
    public RouterFunction<ServerResponse> poner(PonerCartaEnTableroUseCase usecase) {
        return route(
                POST("/juego/poner").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(PonerCartaEnTableroCommand.class))
                        .then(ServerResponse.ok().build())

        );
    }


    @Bean
    public RouterFunction<ServerResponse> iniciarRonda(IniciarRondaUseCase usecase) {
        return route(
                POST("/juego/ronda/iniciar").and(accept(MediaType.APPLICATION_JSON)),
                request -> usecase.andThen(integrationHandle)
                        .apply(request.bodyToMono(IniciarRondaCommand.class))
                        .then(ServerResponse.ok().build())

        );
    }

//    @Bean
//    public RouterFunction<ServerResponse> crear(CrearJuegoUseCase usecase) {
//
//        return route(
//                POST("/juego/crear").and(accept(MediaType.APPLICATION_JSON)),
//                request -> {
//                    System.out.println(request);
//                    return usecase.andThen(integrationHandle)
//                            .apply(request.bodyToMono(CrearJuegoCommand.class))
//                            .then(ServerResponse.ok().build());
//                }
//
//        );
//    }
}
