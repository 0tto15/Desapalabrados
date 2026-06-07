package com.iessanalberto.dam1.desapalabrados.service;

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

    // 1. Creamos "dobles" de acción (Mocks) que fingirán ser la base de datos
    @Mock
    private IPalabraRepository palabraRepositoryMock;

    @Mock
    private IRankingRepository rankingRepositoryMock;

    // La clase real que vamos a someter a prueba
    private PalabraService palabraService;

    @BeforeEach
    void setUp() {
        // [ARRANGE - Preparar]
        // Le enseñamos al mock cómo debe comportarse cuando le pidan palabras
        List<String> palabrasSimuladas = Arrays.asList("JAVA", "MOCKITO", "TEST");
        when(palabraRepositoryMock.obtenerTodasLasPalabras()).thenReturn(palabrasSimuladas);

        // Instanciamos el servicio pasándole los mocks en lugar de los repositorios reales
        palabraService = new PalabraService(palabraRepositoryMock, rankingRepositoryMock);
    }

    @Test
    void comprobarQueElServicioCargaLasPalabrasAlIniciar() {
        // [ACT - Actuar]
        // La acción ya ocurrió en el setUp() al crear el new PalabraService(),
        // ya que el constructor llama a inicializarNuevaPartida().

        // [ASSERT - Comprobar]
        // Verificamos que el servicio efectivamente llamó al método del repositorio 1 vez
        verify(palabraRepositoryMock, times(1)).obtenerTodasLasPalabras();
    }
}