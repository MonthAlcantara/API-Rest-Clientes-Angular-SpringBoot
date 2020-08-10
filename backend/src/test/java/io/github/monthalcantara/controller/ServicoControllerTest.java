package io.github.monthalcantara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.model.Servico;
import io.github.monthalcantara.clientes.service.interfaces.ServicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ServicoControllerTest {

    static String SERVICO_API = "/api/servicos";

    Servico servico;
    Cliente cliente;

    @Autowired
    MockMvc mvc;

    @MockBean
    ServicoService servicoService;


    @Test
    @DisplayName("Deve retornar um cliente pelo Id")
    public void deveRetornarClienteSalvo() throws Exception {
        cliente = geradorDeCliente();
        servico = geradorDeServico();

        BDDMockito
                .given(servicoService.findById(Mockito.anyInt()))
                .willReturn(servico);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(SERVICO_API.concat("/" + servico.getId()))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("cliente").value(cliente))
                .andExpect(jsonPath("id").value(servico.getId()))
                .andExpect(jsonPath("descricao").value(servico.getDescricao()))
                .andExpect(jsonPath("valor").value(servico.getValor()));

    }

    @Test
    @DisplayName("Deve criar um serviço")
    public void deveCriarUmServico() throws Exception {
        cliente = geradorDeCliente();
        servico = geradorDeServico();
        BDDMockito
                .given(servicoService.save(Mockito.any(Servico.class)))
                .willReturn(servico);

        String json = new ObjectMapper().writeValueAsString(servico);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(SERVICO_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("cliente").value(cliente))
                .andExpect(jsonPath("id").value(servico.getId()))
                .andExpect(jsonPath("descricao").value(servico.getDescricao()))
                .andExpect(jsonPath("valor").value(servico.getValor()));

    }

    @Test
    @DisplayName("Deve atualizar um servico se existente")
    public void deveAtualizarUmServico() throws Exception {
        cliente = geradorDeCliente();
        servico = geradorDeServico();
        String json = new ObjectMapper().writeValueAsString(servico);

        BDDMockito
                .given(servicoService.updateServico(Mockito.any(Integer.class), Mockito.any(Servico.class)))
                .willReturn(servico);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(SERVICO_API.concat("/" + 1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk());


    }

    @Test
    @DisplayName("Deve deletar um cliente pelo Id")
    public void deveDeletarCliente() throws Exception {
        cliente = geradorDeCliente();
        servico = geradorDeServico();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(SERVICO_API.concat("/" + servico.getId()))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk());
    }

    private Cliente geradorDeCliente() {
        return Cliente.builder()
                .cpf("16431910044")
                .dataCadastro(null)
                .id(1)
                .nome("Teste")
                .build();
    }

    private Servico geradorDeServico() {
        return Servico.builder()
                .cliente(cliente)
                .id(1)
                .descricao("Teste")
                .valor(BigDecimal.valueOf(1000))
                .build();
    }
}
