package io.github.monthalcantara.clientes.repository;

import io.github.monthalcantara.clientes.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    Optional<Servico> findById(Integer id);

    Servico save(Servico servico);

    void deleteById(Integer id);
}
