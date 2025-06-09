package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class Financeiro extends Setor {

    public Financeiro() throws QuantidadeLimiteFuncionariosException {
        super();
        super.setNome("Financeiro");
        super.setQtdFuncionarios(3);

        if(getContador() > getQtdFuncionarios()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
        }

        setContador(getContador() + 1);
    }
}
