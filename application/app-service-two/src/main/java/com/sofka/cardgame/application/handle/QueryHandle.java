package com.sofka.cardgame.application.handle;

import com.sofka.cardgame.application.handle.model.JuegoListViewModel;
import com.sofka.cardgame.application.handle.model.MazoViewModel;
import com.sofka.cardgame.application.handle.model.TableroViewModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;


@Configuration
public class QueryHandle {

    private final ReactiveMongoTemplate template;

    public QueryHandle(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Bean
    public RouterFunction<ServerResponse> listarJuego() {
        return RouterFunctions.route(
                GET("/juego/listar/{uid}"),
                request -> template.find(filterByUId(request.pathVariable("uid")), JuegoListViewModel.class, "gameview")
                        .collectList()
                        .flatMap(list -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Flux.fromIterable(list), JuegoListViewModel.class)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> listarJuegos() {
        return RouterFunctions.route(
                GET("/juegos/listar"),
                serverRequest -> template.findAll(JuegoListViewModel.class,"gameview")
                        .collectList()
                        .flatMap(games->ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Flux.fromIterable(games),JuegoListViewModel.class))));

    }

    @Bean
    public RouterFunction<ServerResponse> getTablero() {
        return RouterFunctions.route(
                GET("/tablero/{id}"),
                request -> template.findOne(filterById(request.pathVariable("id")), TableroViewModel.class, "gameview")
                        .flatMap(element -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Mono.just(element), TableroViewModel.class)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> mazoPorJugador() {
        return RouterFunctions.route(
                GET("/jugador/mazo/{uid}"),
                request -> template.find(filterByUId(request.pathVariable("uid")), MazoViewModel.class, "mazoview")
                        .collectList()
                        .flatMap(list -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Flux.fromIterable(list), MazoViewModel.class)))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> getMazo() {
        return RouterFunctions.route(
                GET("/juego/mazo/{uid}/{juegoId}"),
                request -> template.findOne(filterByUidAndId(request.pathVariable("uid"), request.pathVariable("juegoId")), MazoViewModel.class, "mazoview")
                        .flatMap(element -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(Mono.just(element), MazoViewModel.class)))
        );
    }


//    private Query filterByUId(String uid) {
//        return new Query(
//                Criteria.where("jugadores."+uid+".jugadorId").is(uid)
//        );
//    }

    private Query filterByUId(String uid) {
        return new Query(
                Criteria.where("uid").is(uid)
        );
    }

    private Query filterById(String juegoId) {
        return new Query(
                Criteria.where("_id").is(juegoId)
        );
    }

    private Query filterByUidAndId(String uid, String juegoId) {
        return new Query(
                Criteria.where("juegoId").is(juegoId).and("uid").is(uid)
        );
    }


}
