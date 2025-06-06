package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class Financeiro extends Setor {

    private int contador = 1;


    public Financeiro(String nome) throws QuantidadeLimiteFuncionariosException {
        super(nome);
        setQtdLimite(3);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }

        contador++;
    }
}
