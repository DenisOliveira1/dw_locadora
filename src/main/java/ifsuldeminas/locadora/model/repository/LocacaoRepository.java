package ifsuldeminas.locadora.model.repository;

import ifsuldeminas.locadora.model.entity.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    @Query("SELECT l FROM Locacao l WHERE l.filme.id = :filmeId")
    List<Locacao> findAllByFilmeId(Long filmeId);

    @Query("SELECT l FROM Locacao l WHERE l.cliente.id = :clienteId")
    List<Locacao> findAllByClienteId(Long clienteId);
}
