package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class GestaoPessoas extends Setor {

    public GestaoPessoas() throws QuantidadeLimiteFuncionariosException {
        super();
        super.setNome("Gestão de Pessoas");
        super.setQtdFuncionarios(4);

        if(getContador() > getQtdFuncionarios()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
        }

        setContador(getContador() + 1);
    }
}
