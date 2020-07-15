package io.github.monthalcantara.controller;

import io.github.monthalcantara.model.Cliente;
import io.github.monthalcantara.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController{

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> findAll(Cliente cliente){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example example = Example.of(cliente, matcher);
        List<Cliente> clientes = clienteService.findAll(example);
        return clientes;

    }

    @GetMapping("/{id}")
    public Cliente buscarPeloId(@PathVariable Integer id){
        return clienteService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        return clienteService.save(cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id){
        clienteService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente){
        clienteService.update(id, cliente);
    }

}
