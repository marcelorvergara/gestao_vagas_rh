package net.mvergara.gestao_vagas.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("Usuário não existente na base");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}
