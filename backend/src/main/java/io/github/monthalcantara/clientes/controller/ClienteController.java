package io.github.monthalcantara.clientes.controller;

import io.github.monthalcantara.clientes.dto.ClienteDTO;
import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.service.interfaces.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public List<ClienteDTO> findAll(Cliente cliente) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example example = Example.of(cliente, matcher);
        List<Cliente> clientes = clienteService.findAll(example);
        return clientes.stream().map(c -> modelMapper.map(c, ClienteDTO.class)).collect(Collectors.toList());


    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO buscarPeloId(@PathVariable Integer id) {

        return modelMapper
                .map(clienteService.findById(id), ClienteDTO.class);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO salvar(@RequestBody @Valid Cliente cliente) {
        return modelMapper
                .map(clienteService.save(cliente), ClienteDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {

        clienteService.delete(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {

        clienteService.update(id, cliente);

    }

}
