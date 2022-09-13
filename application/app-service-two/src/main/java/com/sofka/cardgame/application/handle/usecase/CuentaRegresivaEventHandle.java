package com.sofka.cardgame.application.handle.usecase;

import com.sofka.cardgame.application.handle.IntegrationHandle;
import com.sofka.cardgame.events.RondaIniciada;
import com.sofka.cardgame.usecases.IniciarCuentaRegresivaUseCase;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Mono;



@Configuration
public class CuentaRegresivaEventHandle {

    private final IniciarCuentaRegresivaUseCase usecase;

    private final IntegrationHandle handle;

    public CuentaRegresivaEventHandle(IniciarCuentaRegresivaUseCase usecase, IntegrationHandle handle) {
        this.usecase = usecase;
        this.handle = handle;
    }

    @EventListener
    public void handleIniciarCuentaRegresiva(RondaIniciada event) {
        usecase.andThen(handle).apply(Mono.just(event)).block();
    }


}