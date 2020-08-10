package io.github.monthalcantara.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.monthalcantara.clientes.model.Cliente;
import io.github.monthalcantara.clientes.service.interfaces.ClienteService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class ClienteControllerTest {

    static String CLIENTE_API = "/api/clientes";

    @Autowired
    MockMvc mvc;

    @MockBean
    ClienteService clienteService;

    @Test
    @DisplayName("Deve criar um cliente com sucesso")
    public void deveCriarClienteTest() throws Exception {
        Cliente cliente = Cliente.builder().id(1).cpf("16431910044").nome("Teste").build();

        BDDMockito.given(clienteService.save(Mockito.any(Cliente.class)))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(cliente);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(CLIENTE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("nome").value(cliente.getNome()))
                .andExpect(jsonPath("cpf").value(cliente.getCpf()));
    }

    @Test
    @DisplayName("Deve lançar erro criar um cliente sem dados suficientes")
    public void naoDeveCriarClienteinvalidoTest() {

    }


}
