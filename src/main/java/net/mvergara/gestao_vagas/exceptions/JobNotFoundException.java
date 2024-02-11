package net.mvergara.gestao_vagas.exceptions;

public class JobNotFoundException extends RuntimeException{
    public JobNotFoundException() {
        super("Vaga não existente na base");
    }
}
