package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class AtendimentoCliente extends Setor {

    private int contador = 1;


    public AtendimentoCliente(String nome) throws QuantidadeLimiteFuncionariosException {
        super(nome);
        setQtdLimite(4);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }

        contador++;
    }
}
