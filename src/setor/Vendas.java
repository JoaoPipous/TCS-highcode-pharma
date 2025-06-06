package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class Vendas extends Setor {

    private int contador = 1;


    public Vendas(String nome) throws QuantidadeLimiteFuncionariosException {
        super(nome);
        setQtdLimite(5);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }

        contador++;
    }
}
