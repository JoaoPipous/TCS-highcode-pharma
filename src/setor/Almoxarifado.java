package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class Almoxarifado extends Setor {

    private int contador = 1;


    public Almoxarifado(String nome, int qtdFuncionarios) throws QuantidadeLimiteFuncionariosException {
        super(nome, qtdFuncionarios);
        setQtdLimite(3);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }

        contador++;
    }
}
