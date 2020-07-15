package io.github.monthalcantara.clientes.service.implementacao;

import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.repository.ClienteRepository;
import io.github.monthalcantara.clientes.exceptions.RecursoNotFound;
import io.github.monthalcantara.clientes.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll(Example example) {
        return clienteRepository.findAll(example);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            clienteRepository.deleteById(id);
        }

    }

    @Override
    public void update(Integer id, Cliente cliente) {
        findById(id);
        cliente.setId(id);
        save(cliente);
    }

    @Override
    public Cliente findById(Integer id) {
        return clienteRepository
                .findById(id)
                .orElseThrow(() -> new RecursoNotFound("Cliente não encontrado"));
    }
}
