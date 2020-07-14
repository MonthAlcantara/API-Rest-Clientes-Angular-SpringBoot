package io.github.monthalcantara.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @NotEmpty(message = "Nescessário informar a descrição")
   @Column(length = 120, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private BigDecimal valor;
}
