package com.apitareas.usuarios.service;

import com.apitareas.usuarios.port.UsuarioPort;
import com.apitareas.usuarios.request.UsuarioRequest;
import com.apitareas.usuarios.response.UsuarioResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioPort port;

    @Autowired
    @InjectMocks
    private UsuarioService service;

    UsuarioRequest request;
    UsuarioResponse usuario;
    List<UsuarioResponse> listaUsuarios;

    @BeforeEach
    void setUp() {
        usuario = UsuarioResponse.builder()
                .id(1)
                .nombre("Andres")
                .cedula("12345")
                .fechaCreacion(LocalDate.now())
                .build();
        listaUsuarios = List.of(UsuarioResponse.builder()
                .id(1)
                .nombre("Andres")
                .cedula("12345")
                .fechaCreacion(LocalDate.now())
                .build());
        request = UsuarioRequest.builder()
                .nombre("Andres")
                .cedula("12345")
                .build();
    }

    @Test
    void guardarUsuario() {
        doNothing().when(port).guardarUsuario(any(UsuarioRequest.class));
        service.guardarUsuario(request);
        verify(port, times(1)).guardarUsuario(request);
    }

    @Test
    void listaUsuarios() {
        when(port.listaUsuarios()).thenReturn(listaUsuarios);
        assertThat(service.listaUsuarios()).hasSize(listaUsuarios.size());
    }

    @Test
    void buscarUsuario() {
        when(port.buscarUsuario(any(Integer.class))).thenReturn(usuario);
        assertThat(service.buscarUsuario(1)).isNotNull();
    }

    @Test
    void usuarioExiste() {
        when(port.buscarUsuario(any(Integer.class))).thenReturn(usuario);
        assertThat(service.buscarUsuario(1)).isNotNull();
    }
}