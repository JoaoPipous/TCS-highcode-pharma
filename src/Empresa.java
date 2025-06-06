import exception.QuantidadeLimiteFuncionariosException;
import model.Caixa;
import setor.Almoxarifado;
import setor.Setor;

import java.util.ArrayList;

public class Empresa {
    private Caixa caixa;
    private Almoxarifado almoxarifado;
    private ArrayList<Setor> setores;

    public Empresa(){
        this.caixa = new Caixa(200000);
        try {
            this.almoxarifado = new Almoxarifado("Almoxarifado");
        } catch(QuantidadeLimiteFuncionariosException e) {
            e.printStackTrace();
        }
        setores = new ArrayList<>();
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }

    public ArrayList<Setor> getSetores() {
        return setores;
    }

    public void adicionarVenda() {}

    public void criarSetor(String nome) {
        setores.add(new Setor(nome));
    }
}