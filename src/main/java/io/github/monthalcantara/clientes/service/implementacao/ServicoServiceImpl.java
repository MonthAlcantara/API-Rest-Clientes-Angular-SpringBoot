package io.github.monthalcantara.clientes.service.implementacao;

import io.github.monthalcantara.clientes.exceptions.RecursoNotFound;
import io.github.monthalcantara.clientes.model.Servico;
import io.github.monthalcantara.clientes.repository.ServicoRepository;
import io.github.monthalcantara.clientes.service.interfaces.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    public ServicoRepository servicoRepository;

    @Override
    public List<Servico> findAll(Example example) {
        return servicoRepository.findAll(example);
    }

    @Override
    public Servico findById(Integer id) {
        return servicoRepository.findById(id).orElseThrow(() -> new RecursoNotFound("Servico não encontrado"));
    }

    @Override
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public Servico updateServico(Integer id, Servico servico) {
        Servico servicoEncontrado = servicoRepository.findById(id)
                .orElseThrow(() -> new RecursoNotFound("Servico não encontrado"));
        servico.setId(servicoEncontrado.getId());
        return servicoRepository.save(servico);
    }

    @Override
    public void deleteById(Integer id) {
        servicoRepository.deleteById(id);
    }
}
