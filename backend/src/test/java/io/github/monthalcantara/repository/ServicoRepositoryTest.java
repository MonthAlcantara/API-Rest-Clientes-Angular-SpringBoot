package io.github.monthalcantara.repository;

import io.github.monthalcantara.clientes.exceptions.RecursoNotFound;
import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.model.Servico;
import io.github.monthalcantara.clientes.service.interfaces.ClienteService;
import io.github.monthalcantara.clientes.service.interfaces.ServicoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class ServicoRepositoryTest {

    Servico servico, servicoSalvo;
    Cliente cliente;

    @Autowired
    ServicoService servicoService;
    @Autowired
    ClienteService clienteService;

    @Test
    @DisplayName("Deve criar um novo servico")
    public void deveCriarServico() {
        cliente = geradorDeCliente();
        cliente = clienteService.save(cliente);
        servico = geradorDeServico();
        servicoSalvo = servicoService.save(servico);
        Assertions.assertThat(servicoSalvo).isNotNull();
    }

    @Test
    @DisplayName("Deve deletar um serviço")
    public void deveDeletarServico() {
        cliente = geradorDeCliente();
        cliente = clienteService.save(cliente);
        servico = geradorDeServico();
        servicoService.save(servico);
        servicoService.deleteById(servico.getId());
        RuntimeException runtimeException = org.junit.jupiter.api.Assertions
                .assertThrows(RecursoNotFound.class, () -> servicoService.findById(servico.getId()));
        org.junit.jupiter.api.Assertions.assertTrue(runtimeException.getMessage().contains("Servico não encontrado"));

    }

    @Test
    @DisplayName("Deve lançar erro ao deletar serviço inexistente")
    public void naoDeveDeletarServicoInexistente() {

        RuntimeException runtimeException = org.junit.jupiter.api.Assertions
                .assertThrows(RecursoNotFound.class, () -> servicoService.deleteById(2));
        org.junit.jupiter.api.Assertions.assertTrue(runtimeException.getMessage().contains("Servico não encontrado"));

    }

    @Test
    @DisplayName("Deve lançar erro ao buscar um serviço inexistente")
    public void naoDeveBuscarServicoInexistente() {

        RuntimeException runtimeException = org.junit.jupiter.api.Assertions
                .assertThrows(RecursoNotFound.class, () -> servicoService.findById(10));
        org.junit.jupiter.api.Assertions.assertTrue(runtimeException.getMessage().contains("Servico não encontrado"));

    }

    @Test
    @DisplayName("Deve buscar serviço")
    public void deveBuscarServico() {
        cliente = geradorDeCliente();
        cliente = clienteService.save(cliente);
        servico = geradorDeServico();
        servico = servicoService.save(servico);
        servicoSalvo = servicoService.findById(servico.getId());
        Assertions.assertThat(servicoSalvo).isNotNull();
    }

    @Test
    @DisplayName("Deve atualizar um serviço")
    public void deveAtualizarUmServico() {
        cliente = geradorDeCliente();
        cliente = clienteService.save(cliente);
        servico = geradorDeServico();
        servicoSalvo = servicoService.save(servico);
        servicoSalvo.setDescricao("Teste2");
        servicoSalvo.setValor(BigDecimal.valueOf(2000));
        servicoSalvo.setId(servico.getId());
        servicoService.save(servicoSalvo);
        Assertions.assertThat(servico.getDescricao()).isNotEqualTo(servicoSalvo.getDescricao());
        Assertions.assertThat(servico.getValor()).isNotEqualTo(servicoSalvo.getValor());
        Assertions.assertThat(servico.getId()).isEqualTo(servicoSalvo.getId());

    }

    private Servico geradorDeServico() {
        return Servico.builder()
                .cliente(cliente)
                .id(1)
                .descricao("Teste")
                .valor(BigDecimal.valueOf(1000))
                .build();
    }

    private Cliente geradorDeCliente() {
        return Cliente.builder()
                .cpf("16431910044")
                .dataCadastro(LocalDateTime.parse("2020-08-06T07:34:35"))
                .id(11)
                .nome("Teste")
                .build();
    }
}
