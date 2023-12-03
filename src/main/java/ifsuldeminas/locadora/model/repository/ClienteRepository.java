package ifsuldeminas.locadora.model.repository;

import ifsuldeminas.locadora.model.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
