package io.github.monthalcantara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "O campo nome não pode ser vazio")
    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 11)
    @NotEmpty(message = "Obrigatório informar o CPF")
    private String cpf;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

}
