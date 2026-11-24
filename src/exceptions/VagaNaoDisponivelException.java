package exceptions;

public class VagaNaoDisponivelException extends Exception {
    public VagaNaoDisponivelException(String mensagem) {
        super(mensagem);
    }
}