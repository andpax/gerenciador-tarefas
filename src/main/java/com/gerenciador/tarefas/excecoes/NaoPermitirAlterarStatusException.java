package com.gerenciador.tarefas.excecoes;

public class NaoPermitirAlterarStatusException extends RuntimeException {

    public NaoPermitirAlterarStatusException() {
        super();
    }

    public NaoPermitirAlterarStatusException(String mensagem) {
        super(mensagem);
    }
}
