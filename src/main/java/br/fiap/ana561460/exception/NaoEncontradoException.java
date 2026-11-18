package br.fiap.ana561460.exception;

    public class NaoEncontradoException extends RuntimeException {
        public NaoEncontradoException() {
            super("Registro não encontrado.");
        }
        public NaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }

