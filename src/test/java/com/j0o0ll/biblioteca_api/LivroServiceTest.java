package com.j0o0ll.biblioteca_api;

import com.j0o0ll.biblioteca_api.model.Livro;
import com.j0o0ll.biblioteca_api.repository.LivroRepository;
import com.j0o0ll.biblioteca_api.service.LivroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private LivroService service;

    private Livro livro;

    @BeforeEach
    void setUp() {
        livro = new Livro("O Hobbit", "Tolkien", 49.90, 1937);
    }

    @Test
    void deveListarTodosOsLivros() {
        when(repository.findAll()).thenReturn(List.of(livro));

        List<Livro> resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("O Hobbit", resultado.get(0).getTitulo());
    }

    @Test
    void deveRetornarVazioQuandoLivroNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        Optional<Livro> resultado = service.buscarPorId(99L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveLancarExcecaoAoEmprestarLivroIndisponivel() {
        livro.setDisponivel(false);
        when(repository.findById(1L)).thenReturn(Optional.of(livro));

        assertThrows(IllegalStateException.class, () -> service.emprestar(1L));
    }
}