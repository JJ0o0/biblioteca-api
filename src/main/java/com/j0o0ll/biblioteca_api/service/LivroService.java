package com.j0o0ll.biblioteca_api.service;

import com.j0o0ll.biblioteca_api.model.Livro;
import com.j0o0ll.biblioteca_api.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Livro> buscarPorAutor(String autor) {
        return repository.findByAutor(autor);
    }

    public Livro salvar(Livro livro) {
        return repository.save(livro);
    }

    public Optional<Livro> atualizar(Long id, Livro dados) {
        return repository.findById(id)
                .map(livro -> {
                    livro.setTitulo(dados.getTitulo());
                    livro.setAutor(dados.getAutor());
                    livro.setPreco(dados.getPreco());
                    livro.setAnoPublicacao(dados.getAnoPublicacao());

                    return repository.save(livro);
                });
    }

    public boolean deletar(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }

    public Optional<Livro> emprestar(Long id) {
        return repository.findById(id)
                .map(livro -> {
                    if (!livro.isDisponivel()) {
                        throw new IllegalStateException("Livro indisponível!");
                    }

                    livro.setDisponivel(false);
                    return repository.save(livro);
                });
    }

    public Optional<Livro> devolver(Long id) {
        return repository.findById(id)
                .map(livro -> {
                    livro.setDisponivel(true);
                    return repository.save(livro);
                });
    }
}
