package io.github.monthalcantara.clientes.service.interfaces;

import io.github.monthalcantara.clientes.model.Servico;

import java.util.List;

public interface ServicoService {
    List<Servico> findAll();

    Servico findById(Integer id);

    Servico save(Servico servico);

    Servico updateServico(Integer id, Servico servico);

    void deleteById(Integer id);
}
