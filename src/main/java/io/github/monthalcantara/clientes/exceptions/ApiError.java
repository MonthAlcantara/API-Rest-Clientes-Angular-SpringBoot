package io.github.monthalcantara.clientes.exceptions;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError{

    @Getter
    private List<String> erros;

    public ApiError(List<String> erros) {
        this.erros = erros;
    }


    public ApiError(String e) {
        this.erros = Arrays.asList(e);
    }

}