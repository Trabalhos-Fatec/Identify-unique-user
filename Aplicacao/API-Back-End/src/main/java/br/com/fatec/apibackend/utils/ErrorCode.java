package br.com.fatec.apibackend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_CODE("not.found.code", "Registro não encontrado"),
    EMAIL_DUPLICATE("email.is.duplicate", "O email já existe");

    @Getter
    private final String code;

    @Getter
    private final String description;
}
