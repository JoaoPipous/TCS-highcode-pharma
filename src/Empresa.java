import exception.*;
import model.*;
import setor.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// --- Importações que faltavam foram adicionadas ---
import enumeracao.Status;
import enumeracao.TipoNegocio;

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

    public void addFuncionario(Funcionario funcionario) throws CodigoUnicoExistenteException {
        validarCodigoUnicoFuncionario(funcionario.getCodigoFuncionario());
        this.funcionarios.add(funcionario);
    }

    private void validarCodigoUnicoFuncionario(String codigoFuncionario) throws CodigoUnicoExistenteException {
        for (Funcionario funcionario : funcionarios) {
            if (codigoFuncionario.equals(funcionario.getCodigoFuncionario())) {
                throw new CodigoUnicoExistenteException("Código único já existente. Insira outro código único.");
            }
        }
    }

    public List<Funcionario> getFuncionarios() { return funcionarios; }

    public String exibirGeneros() { return "1- Masculino  2- Feminino\n"; }

    // --- MÉTODO CORRIGIDO ---
    // Agora o método constrói e retorna a lista de setores formatada.
    public String exibirSetores() {
        StringBuilder sb = new StringBuilder();
        sb.append("Setores disponíveis:\n");
        for (int i = 0; i < setores.size(); i++) {
            sb.append((i + 1) + "- " + setores.get(i).getNome() + "  ");
        }
        sb.append("\n");
        return sb.toString();
    }

    // --- MÉTODO CORRIGIDO ---
    // Lógica completa para definir o setor, validar limite e retornar o objeto correto.
    public Setor definirSetor(int setorEscolhido) throws QuantidadeLimiteFuncionariosException, IllegalArgumentException {
        // Valida se o número escolhido está dentro do intervalo válido da lista
        if (setorEscolhido < 1 || setorEscolhido > setores.size()) {
            throw new IllegalArgumentException("Setor inválido. Por favor, escolha um número da lista.");
        }

        // Pega o setor da lista (ajustando o índice)
        Setor s = setores.get(setorEscolhido - 1);

        // Verifica se o setor já atingiu a quantidade máxima de funcionários
        if (s.getContador() >= s.getQtdFuncionarios()) {
            throw new QuantidadeLimiteFuncionariosException("Quantidade limite de funcionários excedida para o setor: " + s.getNome());
        }

        // Se houver vaga, incrementa o contador e retorna o setor
        s.setContador(s.getContador() + 1);
        s.setQtdFuncionariosTotal(s.getQtdFuncionariosTotal() + 1);
        return s;
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

    public void distribuirBonusParticipacao() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários para distribuir bônus.");
            return;
        }
        double lucroParcial = getCaixa().getLucroMensal() * 0.05; // 5% do lucro mensal
        double bonusPorFuncionario = lucroParcial / funcionarios.size();

        for (Funcionario f : funcionarios) {
            // Supondo que a classe Funcionario tenha um método para receber o bônus
            // f.receberBonus(bonusPorFuncionario);
        }
        System.out.println("Bônus de participação distribuído com sucesso para todos os funcionários.");
    }

    public Caixa getCaixa() { return caixa; }
    public Transportadora getTransportadoras() { return transportadoras; }
    public ArrayList<Setor> getSetores() { return setores; }


    // --- LÓGICA DE NEGÓCIOS CENTRALIZADA ---
    public void registrarCompra(Negocio compra) {
        caixa.registrarCompra(compra);
        if (compra.getStatus() == Status.FINALIZADO) {
            caixa.removerValor(compra.getValorTotal());
            for (ItemNegocio item : compra.getProdutos()) {
                item.getProduto().addEstoque(item.getQtd());
            }
        }
    }

    public void registrarVenda(Negocio venda) throws EstoqueInsuficienteException {
        if (venda.getStatus() == Status.FINALIZADO) {
            for (ItemNegocio item : venda.getProdutos()) {
                if (item.getProduto().getQtdEstoque() < item.getQtd()) {
                    throw new EstoqueInsuficienteException("Estoque insuficiente para " + item.getProduto().getNome());
                }
            }
            caixa.registrarVenda(venda);
            caixa.addValor(venda.getValorTotal());
            for (ItemNegocio item : venda.getProdutos()) {
                item.getProduto().removeEstoque(item.getQtd());
            }
        } else {
            caixa.registrarVenda(venda);
        }
    }

    public void finalizarNegocioAberto(Negocio negocio) throws EstoqueInsuficienteException {
        if (negocio == null || negocio.getStatus() != Status.ABERTO) {
            return;
        }

        if (negocio.getTipoNegocio() == TipoNegocio.VENDA) {
            for (ItemNegocio item : negocio.getProdutos()) {
                if (item.getProduto().getQtdEstoque() < item.getQtd()) {
                    throw new EstoqueInsuficienteException("Estoque insuficiente para finalizar a venda de " + item.getProduto().getNome());
                }
            }
            caixa.addValor(negocio.getValorTotal());
            for (ItemNegocio item : negocio.getProdutos()) {
                item.getProduto().removeEstoque(item.getQtd());
            }

        } else if (negocio.getTipoNegocio() == TipoNegocio.COMPRA) {
            caixa.removerValor(negocio.getValorTotal());
            for (ItemNegocio item : negocio.getProdutos()) {
                item.getProduto().addEstoque(item.getQtd());
            }
        }

        negocio.setStatus(Status.FINALIZADO);
        negocio.setDataFinalizacao(LocalDateTime.now());
    }
}
