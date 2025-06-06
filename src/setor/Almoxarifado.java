package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class Almoxarifado extends Setor {

    public Almoxarifado() throws QuantidadeLimiteFuncionariosException {
        super();
        super.setNome("Almoxarifado");
        super.setQtdFuncionarios(3);

        if(getContador() > getQtdFuncionarios()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu o limite de funcionários.");
        }

        setContador(getContador() + 1);
    }
}
