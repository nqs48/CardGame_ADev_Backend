package com.sofka.cardgame.application.handle.usecase;


import com.sofka.cardgame.application.handle.IntegrationHandle;
import com.sofka.cardgame.events.RondaTerminada;
import com.sofka.cardgame.usecases.CrearRondaUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Mono;


@Configuration
public class CrearRondaEventHandle {

    private final CrearRondaUseCase usecase;

    private final IntegrationHandle handle;

    public CrearRondaEventHandle(CrearRondaUseCase usecase, IntegrationHandle handle) {
        this.usecase = usecase;
        this.handle = handle;
    }

    @EventListener
    public void handleCrearRonda(RondaTerminada event) {
        handle.apply(usecase.apply(Mono.just(event))).block();
    }


}