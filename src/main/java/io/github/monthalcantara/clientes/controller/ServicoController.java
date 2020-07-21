package io.github.monthalcantara.clientes.controller;

import io.github.monthalcantara.clientes.model.Servico;
import io.github.monthalcantara.clientes.service.interfaces.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    public ServicoService servicoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Servico> findAll(Servico servico){
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase();
        Example example = Example.of(servico, matcher);
        return servicoService.findAll(example);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Servico findById(@PathVariable Integer id){
    return servicoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Servico save(@RequestBody @Valid Servico servico){
    return servicoService.save(servico);
    }

    @PutMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public Servico updateServico(@PathVariable Integer id, @RequestBody Servico servico){
        return servicoService.updateServico(id, servico);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id){
        servicoService.deleteById(id);
    }

}
