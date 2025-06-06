import enumeracao.Status;
import enumeracao.TipoNegocio;
import exception.QuantidadeLimiteFuncionariosException;
import model.Caixa;
import model.Funcionario;
import model.ItemNegocio;
import model.Negocio;
import setor.Almoxarifado;
import setor.Setor;

import java.time.LocalDateTime;
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

    public void criarSetor(String nome) {
        setores.add(new Setor(nome));
    }

    public void registrarCompra(Negocio compra) {
        caixa.registrarCompra(compra);
        for(ItemNegocio item : compra.getProdutos()) {
            item.getProduto().addEstoque(item.getQtd());
        }
    }

    public void registrarVenda(Negocio venda) {
        caixa.registrarVenda(venda);
        for(ItemNegocio item : venda.getProdutos()) {
            item.getProduto().removeEstoque(item.getQtd());
        }
    }
}