package model;

import enumeracao.Status;
import enumeracao.TipoNegocio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Negocio {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    private final LocalDateTime dataNegocio = LocalDateTime.now();
    private double valorNegocio;
    private Status status;
    private ArrayList<Funcionario> funcionariosEnvolvidos;
    private ArrayList<ItemNegocio> produtos;
    private LocalDateTime dataProgramada;
    private TipoNegocio tipo;

    // *** APENAS PARA TESTES, REMOVER DEPOIS ***
    public Negocio(Status status, ArrayList<ItemNegocio> itens, TipoNegocio tipo) {
        this.status = status;
        this.produtos = itens;
        this.tipo = tipo;
        this.valorNegocio = calcularValorTotal();
    }

    public Negocio(Status status, ArrayList<Funcionario> funcionariosEnvolvidos, ArrayList<ItemNegocio> itens, TipoNegocio tipo) {
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produtos = itens;
        this.tipo = tipo;
        this.valorNegocio = calcularValorTotal();
    }

    // LISTA DE FUNCIONARIOS REMOVIDA, ADICIONAR NOVAMENTE DEPOIS
    public Negocio(Status status, ArrayList<ItemNegocio> itens, ArrayList<Funcionario> funcionariosEnvolvidos, LocalDateTime dataProgramada, TipoNegocio tipo) {
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produtos = itens;
        this.dataProgramada = dataProgramada;
        this.tipo = tipo;
        this.valorNegocio = calcularValorTotal();
    }

    public String getDataNegocioFormatada() {
        return formatter.format(dataNegocio);
    }

    public String getDataProgramadaFormatada() {
        return formatter.format(dataProgramada);
    }

    public LocalDateTime getDataNegocio() {
        return dataNegocio;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Funcionario> getFuncionariosEnvolvidos() {
        return funcionariosEnvolvidos;
    }

    public ArrayList<ItemNegocio> getProdutos() {
        return produtos;
    }

    public LocalDateTime getDataProgramada() {
        return dataProgramada;
    }

    public ArrayList<String> funcionariosEnvolvidosToString() {
        ArrayList<String> funcionarios = new ArrayList<>();

        for (Funcionario funcionario : funcionariosEnvolvidos) {
            funcionarios.add(funcionario.getCodigoFuncionario() + " - " + funcionario.getNome() + " " + funcionario.getSobrenome());
        }

        return funcionarios;
    }

    public double calcularValorTotal() {
        double soma = 0;
        if(tipo.equals(TipoNegocio.VENDA)) {
            for(ItemNegocio item : produtos) {
                soma += item.getProduto().getValorVenda() * item.getQtd();
            }
        } else {
            for(ItemNegocio item : produtos) {
                soma += item.getProduto().getValorCompra() * item.getQtd();
            }
        }
        return soma;
    }

    public String exibirDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("TIPO DE NEGÓCIO: ").append(this.tipo).append("\n");
        sb.append("   - Status: ").append(this.status).append("\n");
        sb.append("   - Data de Registro: ").append(this.getDataNegocioFormatada()).append("\n");

        // Adiciona a data programada apenas se ela existir
        if (this.dataProgramada != null) {
            sb.append("   - Data Programada: ").append(this.getDataProgramadaFormatada()).append("\n");
        }

        sb.append("\n   --- Itens do Negócio ---\n");
        for (ItemNegocio item : this.produtos) {
            sb.append(String.format("   - Produto: %-25s | Qtd: %-5d | Valor Unit.: R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQtd(),
                    (tipo == TipoNegocio.VENDA ? item.getProduto().getValorVenda() : item.getProduto().getValorCompra())
            ));
        }

        // Adiciona o valor total formatado como moeda
        sb.append(String.format("\n   VALOR TOTAL DA " + String.valueOf(tipo).toUpperCase() + ": R$ %.2f\n", this.valorNegocio));

        // Adiciona os funcionários apenas se a lista não for nula ou vazia
        if (this.funcionariosEnvolvidos != null && !this.funcionariosEnvolvidos.isEmpty()) {
            sb.append("\n   --- Funcionários Envolvidos ---\n");
            for(String funcionario : funcionariosEnvolvidosToString()) {
                sb.append("   - ").append(funcionario).append("\n");
            }
        }

        sb.append("----------------------------------------\n");

        return sb.toString();
    }
}
