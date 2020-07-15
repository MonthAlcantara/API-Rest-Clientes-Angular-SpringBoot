package io.github.monthalcantara.clientes.repository;

import io.github.monthalcantara.clientes.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {
}
