package ifsuldeminas.locadora.model.repository;

import ifsuldeminas.locadora.model.entity.filme.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
