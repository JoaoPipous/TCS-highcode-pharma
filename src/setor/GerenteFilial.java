package setor;

import exception.QuantidadeLimiteFuncionariosException;

public class GerenteFilial extends Setor {

    private int contador = 1;


    public GerenteFilial(String nome) throws QuantidadeLimiteFuncionariosException {
        super(nome);
        setQtdLimite(1);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }

        contador++;
    }
}
