package com.iessanalberto.dam1.desapalabrados;

import com.iessanalberto.dam1.desapalabrados.repository.IPalabraRepository;
import com.iessanalberto.dam1.desapalabrados.repository.IRankingRepository;
import com.iessanalberto.dam1.desapalabrados.services.PalabraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PalabraServiceTest {

    // Se inicializan las interfaces creadas
    @Mock
    private IPalabraRepository palabraRepositoryMock;
    @Mock
    private IRankingRepository rankingRepositoryMock;
    private PalabraService palabraService;

    @BeforeEach
    void setUp() {
        List<String> palabrasSimuladas = Arrays.asList("PALABRA", "PALABRO", "PALUBRU");
        when(palabraRepositoryMock.obtenerTodasLasPalabras()).thenReturn(palabrasSimuladas);

        // Instanciamos el servicio pasándole los mocks en lugar de los repositorios reales
        palabraService = new PalabraService(palabraRepositoryMock, rankingRepositoryMock);
    }

    @Test
    void comprobarQueElServicioCargaLasPalabrasAlIniciar() {
        // Se verifica que el servicio efectivamente llame al método del repositorio 1 vez
        verify(palabraRepositoryMock, times(1)).obtenerTodasLasPalabras();
    }
}