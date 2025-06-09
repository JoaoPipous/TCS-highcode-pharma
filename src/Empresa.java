import exception.CategoriaInvalidaException;
import exception.EstoqueInsuficienteException;
import model.*;
import exception.QuantidadeLimiteFuncionariosException;
import model.ItemNegocio;
import model.Negocio;
import setor.*;
import setor.Almoxarifado;
import setor.Setor;
import exception.CodigoUnicoExistenteException;
import model.Caixa;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private Caixa caixa;
    private ArrayList<Setor> setores;
    private Transportadora transportadoras;
    private List<Funcionario> funcionarios;

    public Empresa() {
        this.caixa = new Caixa(200000);
        this.transportadoras = new Transportadora();
        this.funcionarios = new ArrayList<Funcionario>();
        setores = new ArrayList<>(List.of(new Almoxarifado(), new AtendimentoCliente(), new Financeiro(), new GerenteFilial(), new GestaoPessoas(), new Vendas()));
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

    public Setor definirSetor(int setor) throws QuantidadeLimiteFuncionariosException, IllegalArgumentException {
        for(Setor s : setores) {
            switch (setor) {

                case 1: {
                    if (s instanceof Almoxarifado) {
                        if (s.getContador() > s.getQtdFuncionarios()) {
                            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
                        } else {
                            s.setContador(s.getContador() + 1);
                            s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
                            return s;
                        }
                    }
                    break;
                }

                case 2: {
                    if (s instanceof AtendimentoCliente) {
                        if (s.getContador() > s.getQtdFuncionarios()) {
                            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
                        } else {
                            s.setContador(s.getContador() + 1);
                            s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
                            return s;
                        }
                    }
                    break;
                }

                case 3: {
                    if (s instanceof Financeiro) {
                        if (s.getContador() > s.getQtdFuncionarios()) {
                            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
                        } else {
                            s.setContador(s.getContador() + 1);
                            s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
                            return s;
                        }
                    }
                    break;
                }

                case 4: {
                    if (s instanceof GerenteFilial) {
                        if (s.getContador() > s.getQtdFuncionarios()) {
                            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
                        } else {
                            s.setContador(s.getContador() + 1);
                            s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
                            return s;
                        }
                    }
                    break;
                }

                case 5: {
                    if (s instanceof GestaoPessoas) {
                        if (s.getContador() > s.getQtdFuncionarios()) {
                            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
                        } else {
                            s.setContador(s.getContador() + 1);
                            s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
                            return s;
                        }
                    }
                    break;
                }

                case 6: {
                    if (s instanceof Vendas) {
                        if (s.getContador() > s.getQtdFuncionarios()) {
                            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionários.");
                        } else {
                            s.setContador(s.getContador() + 1);
                            s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
                            return s;
                        }
                    }
                    break;
                }

                default: throw new IllegalArgumentException("Entrada inválida. Setor indefinido.");

            }
        } return null;
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
    }
  
    public String exibirSetores() {
        StringBuilder sb = new StringBuilder();

        int contador = 1;
        for(Setor s : setores) {
            sb.append(contador + "- " + s.getNome() + "  ");
            contador++;
        }
        sb.append("\n");

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

    public ArrayList<Setor> getSetores() { return setores; }

}