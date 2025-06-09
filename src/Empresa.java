import model.Caixa;
import exception.QuantidadeLimiteFuncionariosException;
import model.ItemNegocio;
import model.Negocio;
import setor.Almoxarifado;
import setor.Setor;
import exception.CodigoUnicoExistenteException;
import model.Caixa;
import model.Funcionario;
import model.Transportadora;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private Caixa caixa;
    private Almoxarifado almoxarifado;
    private ArrayList<Setor> setores;
    private Transportadora transportadoras;
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

    public Empresa() {
        this.caixa = new Caixa(200000);
        this.transportadoras = new Transportadora();
        try {
            this.almoxarifado = new Almoxarifado("Almoxarifado");
        } catch(QuantidadeLimiteFuncionariosException e) {
            e.printStackTrace();
        }
        setores = new ArrayList<>();
    }

    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public void validarCodigoUnicoFuncionario(String codigoFuncionario) throws CodigoUnicoExistenteException {
        for(Funcionario funcionario: funcionarios) {
            if(codigoFuncionario.equals(funcionario.getCodigoFuncionario())) {
                throw new CodigoUnicoExistenteException("Código único já existente. Insira outro código único.");
            }
        }
    }

    public String exibirGeneros() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- Masculino  2- Feminino\n");
        return sb.toString();
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
  
    public String exibirSetores() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- Gerente de Filial  2- Atendimento ao Cliente  3- Gestão de Pessoas  4- Financeiro  5- Vendas  6- Almoxarifado\n");
        return sb.toString();
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public Transportadora getTransportadoras() { return transportadoras; }
}