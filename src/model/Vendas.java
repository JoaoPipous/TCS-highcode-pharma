package model;

import exception.QuantidadeLimiteFuncionariosException;

public class Vendas extends Setor {

    private int contador = 1;


    public Vendas(String nome, int qtdFuncionarios) throws QuantidadeLimiteFuncionariosException {
        super(nome,qtdFuncionarios);
        setQtdLimite(5);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }

        contador++;
    }
}
