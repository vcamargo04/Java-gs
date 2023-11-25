package br.com.fiap.rapidmed.model.excecao;

import jakarta.ws.rs.core.Response.ResponseBuilder;

public class ErroInfraestruturaException extends Exception {
    // ...

    public ErroInfraestruturaException(String mensagem, ResponseBuilder response) {
        super(mensagem);
        // Verifique se há algum código que manipula a resposta aqui
    }
}