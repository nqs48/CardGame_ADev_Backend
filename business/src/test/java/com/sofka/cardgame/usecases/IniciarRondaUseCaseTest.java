package com.sofka.cardgame.usecases;

import com.sofka.cardgame.gateway.JuegoDomainEventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IniciarRondaUseCaseTest {

    @InjectMocks
    private IniciarRondaUseCase useCase;


    @Mock
    private JuegoDomainEventRepository repository;

    @Test
    void iniciarRondaHappyPass() {
    }


}