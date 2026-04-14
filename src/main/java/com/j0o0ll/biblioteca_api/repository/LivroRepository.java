package com.j0o0ll.biblioteca_api.repository;

import com.j0o0ll.biblioteca_api.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(String autor);
}
