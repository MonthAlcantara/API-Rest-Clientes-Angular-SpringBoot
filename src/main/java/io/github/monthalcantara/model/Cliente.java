package io.github.monthalcantara.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

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

    @Column(name = "data_cadastro", updatable = false)
    @CreatedDate
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersiste(){
        setDataCadastro(LocalDate.now());
    }
}
