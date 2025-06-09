package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class GerenteFilial extends Setor {

    public GerenteFilial() throws QuantidadeLimiteFuncionariosException {
        super();
        super.setNome("Gerente de Filial");
        super.setQtdFuncionarios(1);

        if(getContador() > getQtdFuncionarios()) {
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
        }

        setContador(getContador() + 1);
    }
}
