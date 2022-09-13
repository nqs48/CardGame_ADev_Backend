package com.sofka.cardgame.application.handle.usecase;

import com.sofka.cardgame.application.handle.IntegrationHandle;
import com.sofka.cardgame.events.RondaTerminada;
import com.sofka.cardgame.usecases.DeterminarGanadorUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Mono;

@Configuration
public class DeterminarGanadorEventHandle {
    private final DeterminarGanadorUseCase usecase;

    private final IntegrationHandle handle;

    public DeterminarGanadorEventHandle(DeterminarGanadorUseCase usecase, IntegrationHandle handle) {
        this.usecase = usecase;
        this.handle = handle;
    }

    @EventListener
    public void handleIniciarCuentaRegresiva(RondaTerminada event) {
        handle.apply(usecase.apply(Mono.just(event))).block();
    }

}