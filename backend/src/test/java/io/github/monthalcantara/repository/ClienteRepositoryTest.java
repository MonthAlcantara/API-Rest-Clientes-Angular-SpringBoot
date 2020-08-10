package io.github.monthalcantara.repository;

import io.github.monthalcantara.clientes.exceptions.RecursoNotFound;
import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.service.interfaces.ClienteService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClienteRepositoryTest {

    Cliente cliente, clienteSalvo;

    @Autowired
    ClienteService clienteService;


    @Test
    @DisplayName("Deve salvar um novo cliente")
    public void deveSalvarNovoUsuario() {
        cliente = clienteService.save(geradorDeCliente());
        Assert.assertNotNull(clienteService.save(cliente));
    }

    @Test
    @DisplayName("Deve deletar cliente salvo se existente")
    public void deveDeletarCliente() {
        cliente = clienteService.save(geradorDeCliente());
        clienteSalvo = clienteService.save(cliente);
        clienteService.delete(1);
        RuntimeException runtimeException = Assertions.assertThrows(RecursoNotFound.class, () -> clienteService.findById(1));
        Assertions.assertTrue(runtimeException.getMessage().contains("Cliente não encontrado pelo Id informado"));

    }

    @Test
    @DisplayName("Não deve deletar cliente salvo se existente")
    public void naoDeveDeletarClienteSeIdNaoExiste() {
        RuntimeException runtimeException = Assertions.assertThrows(RecursoNotFound.class, () -> clienteService.delete(1));
        Assertions.assertTrue(runtimeException.getMessage().contains("Cliente não encontrado pelo Id informado"));

    }

    @Test
    @DisplayName("Deve atualizar dados do cliente")
    public void deveAtualizarCliente() {
        cliente = clienteService.save(geradorDeCliente());
        clienteSalvo = clienteService.save(cliente);
        clienteSalvo.setDataCadastro(LocalDateTime.parse("2020-08-06T10:18:35"));
        clienteSalvo.setCpf("95786556654");
        clienteSalvo.setNome("Teste2");
        clienteSalvo = clienteService.save(clienteSalvo);
        assertThat(cliente.getCpf()).isNotEqualTo(clienteSalvo.getCpf());
        assertThat(cliente.getDataCadastro()).isNotEqualTo(clienteSalvo.getDataCadastro());
        assertThat(cliente.getNome()).isNotEqualTo(clienteSalvo.getNome());

    }

    @Test
    @DisplayName("Deve buscar cliente pelo id")
    public void deveBuscarPeloId() {
        cliente = clienteService.save(geradorDeCliente());
        clienteSalvo = clienteService.save(cliente);
        assertThat(clienteService.findById(clienteSalvo.getId())).isNotNull();
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar cliente pelo id inexistente")
    public void naoDeveBuscarPeloId() {
        cliente = clienteService.save(geradorDeCliente());
        clienteSalvo = clienteService.save(cliente);
        RuntimeException runtimeException = Assertions.assertThrows(RecursoNotFound.class, () -> clienteService.findById(1));
        Assertions.assertTrue(runtimeException.getMessage().contains("Cliente não encontrado pelo Id informado"));

    }

    private Cliente geradorDeCliente() {
        return Cliente.builder()
                .cpf("16431910044")
                .dataCadastro(LocalDateTime.parse("2020-08-06T07:34:35"))
                .id(1)
                .nome("Teste")
                .build();
    }

}
