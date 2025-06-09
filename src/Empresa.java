import exception.EstoqueInsuficienteException;
import model.*;
import exception.QuantidadeLimiteFuncionariosException;
import setor.Almoxarifado;
import setor.Setor;
import exception.CodigoUnicoExistenteException;
import model.Caixa;

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
            this.almoxarifado = new Almoxarifado();
        } catch(QuantidadeLimiteFuncionariosException e) {
            System.out.println("\n" + e.getMessage());
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

    public ArrayList<Setor> getSetores() {
        return setores;
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

    public void registrarVenda(Negocio venda) throws EstoqueInsuficienteException {
        // --- INÍCIO DA VALIDAÇÃO ---
        // 1. Loop para VERIFICAR o estoque de todos os produtos ANTES de fazer qualquer alteração.
        for (ItemNegocio item : venda.getProdutos()) {
            Produto produtoNoEstoque = item.getProduto();
            int quantidadeDesejada = item.getQtd();

            if (produtoNoEstoque.getQtdEstoque() < quantidadeDesejada) {
                // 2. Se um item não tiver estoque, lança um erro e interrompe a operação.
                throw new EstoqueInsuficienteException(
                        "Estoque insuficiente para o produto: " + produtoNoEstoque.getNome() +
                                ". Disponível: " + produtoNoEstoque.getQtdEstoque() + ", Desejado: " + quantidadeDesejada
                );
            }
        }
        // --- FIM DA VALIDAÇÃO ---

        // 3. Se todos os produtos tiverem estoque, a execução continua normalmente.
        // O código abaixo só será executado se a validação passar.
        caixa.registrarVenda(venda);

        for (ItemNegocio item : venda.getProdutos()) {
            item.getProduto().removeEstoque(item.getQtd()); // Agora essa operação é segura
        }

        caixa.registrarVenda(venda);
        for(ItemNegocio item : venda.getProdutos()) {
            item.getProduto().removeEstoque(item.getQtd());
        }
    }

    public Almoxarifado getAlmoxarifado() {
        return almoxarifado;
    }
  
    public String exibirSetores() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- Gerente de Filial  2- Atendimento ao Cliente  3- Gestão de Pessoas  4- Financeiro  5- Vendas  6- Almoxarifado\n");
        return sb.toString();
    }

    public void exibirFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public Transportadora getTransportadoras() { return transportadoras; }
}