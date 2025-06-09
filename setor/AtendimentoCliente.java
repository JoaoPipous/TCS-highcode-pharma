package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class AtendimentoCliente extends Setor {

    public AtendimentoCliente() throws QuantidadeLimiteFuncionariosException {
        super();
        super.setNome("Atendimento ao Cliente");
        super.setQtdFuncionarios(4);

        if(getContador() > getQtdFuncionarios()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
        }

        setContador(getContador() + 1);
    }
}
