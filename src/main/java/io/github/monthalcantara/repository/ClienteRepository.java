package io.github.monthalcantara.repository;

import io.github.monthalcantara.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Override
    List<Cliente> findAll();

    void deleteById(Integer id);
}
