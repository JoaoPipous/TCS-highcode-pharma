package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class Vendas extends Setor {

    public Vendas() throws QuantidadeLimiteFuncionariosException {
        super();
        super.setNome("Vendas");
        super.setQtdFuncionarios(5);

        if(getContador() > getQtdFuncionarios()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
        }

        setContador(getContador() + 1);
    }
}
