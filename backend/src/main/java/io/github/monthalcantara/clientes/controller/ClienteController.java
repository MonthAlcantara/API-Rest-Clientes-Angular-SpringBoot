package io.github.monthalcantara.clientes.controller;

import io.github.monthalcantara.clientes.dto.ClienteDTO;
import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.service.interfaces.ClienteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/clientes")
public class ClienteController {


    ClienteService clienteService;

    ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> findAll(Cliente cliente) {
        log.info("Buscando todos os clientes cadastrados");
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example example = Example.of(cliente, matcher);
        List<Cliente> clientes = clienteService.findAll(example);
        return clientes.stream()
                .map(c -> modelMapper.map(c, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO buscarPeloId(@PathVariable Integer id) {
        log.info("Buscando o cliente cadastrado pelo id: {} ", id);
        return modelMapper
                .map(clienteService.findById(id), ClienteDTO.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO salvar(@RequestBody @Valid Cliente cliente) {
        log.info("Salvando um novo cliente");
        return modelMapper
                .map(clienteService.save(cliente), ClienteDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {
        log.info("Deletando o cliente cadastrado pelo id: {} ", id);
        clienteService.delete(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        log.info("Atualizando o cliente cadastrado pelo id: {} ", id);
        clienteService.update(id, cliente);

    }

}
