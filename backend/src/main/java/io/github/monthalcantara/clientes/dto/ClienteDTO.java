package io.github.monthalcantara.clientes.dto;

import io.github.monthalcantara.clientes.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteDTO {

    private Integer id;

    private String nome;

    private String cpf;

    public ClienteDTO converteParaClienteDTO(Cliente cliente) {
        return ClienteDTO.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .build();
    }

    public Cliente converteDTOParaCliente(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .id(clienteDTO.getId())
                .cpf(clienteDTO.getCpf())
                .nome(clienteDTO.getNome())
                .build();
    }

    public List<ClienteDTO> converteListaClientesParaDTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> converteParaClienteDTO(cliente))
                .collect(Collectors.toList());
    }
}
