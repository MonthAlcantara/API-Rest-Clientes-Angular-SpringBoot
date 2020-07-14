package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
