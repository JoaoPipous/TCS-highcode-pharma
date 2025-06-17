package model;

import enumeracao.Status;
import enumeracao.TipoNegocio;
import enumeracao.Transportadora;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Negocio {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final LocalDateTime dataNegocio = LocalDateTime.now();
    private double valorNegocio;
    private Status status;
    private ArrayList<Funcionario> funcionariosEnvolvidos;
    private ArrayList<ItemNegocio> produtos;
    private LocalDateTime dataProgramada; // Usado para negócios ABERTOS com data futura
    private LocalDateTime dataFinalizacao; // --- ADIÇÃO: Data em que um negócio foi efetivamente finalizado ---
    private TipoNegocio tipo;
    private Transportadora transportadora;

    public Negocio(Status status, ArrayList<Funcionario> funcionariosEnvolvidos, ArrayList<ItemNegocio> itens, TipoNegocio tipo, Transportadora transportadora) {
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produtos = itens;
        this.tipo = tipo;
        this.valorNegocio = calcularValorTotal();
        this.transportadora = transportadora;
    }

    public Negocio(Status status, ArrayList<ItemNegocio> itens, ArrayList<Funcionario> funcionariosEnvolvidos, LocalDateTime dataProgramada, TipoNegocio tipo, Transportadora transportadora) {
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produtos = itens;
        this.dataProgramada = dataProgramada;
        this.tipo = tipo;
        this.valorNegocio = calcularValorTotal();
        this.transportadora = transportadora;
    }

    // --- ADIÇÕES: Getters, Setters e o método de resumo ---
    public TipoNegocio getTipoNegocio() {
        return tipo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public double getValorTotal() {
        return valorNegocio;
    }

    public Transportadora getTransportadora() { return transportadora; }

    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }

    public String exibirResumo() {
        String resumoProdutos = produtos.stream()
                .map(item -> item.getProduto().getNome())
                .collect(Collectors.joining(", "));

        return String.format("Tipo: %s | Status: %s | Produtos: %s | Valor Total: R$%.2f",
                this.tipo, this.status, resumoProdutos, this.valorNegocio);
    }
    // ---------------------------------------------------------

    public String getDataNegocioFormatada() {
        return formatter.format(dataNegocio);
    }

    public String getDataProgramadaFormatada() {
        if(dataProgramada == null) return "N/A";
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
        if (tipo.equals(TipoNegocio.VENDA)) {
            for (ItemNegocio item : produtos) {
                soma += item.getProduto().getValorVenda() * item.getQtd();
            }
        } else {
            for (ItemNegocio item : produtos) {
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

        if (this.dataProgramada != null) {
            sb.append("   - Data Programada: ").append(this.getDataProgramadaFormatada()).append("\n");
        }
        // --- ADIÇÃO: Mostra a data de finalização se o negócio estiver finalizado ---
        if (this.dataFinalizacao != null) {
            sb.append("   - Data de Finalização: ").append(formatter.format(this.dataFinalizacao)).append("\n");
        }

        sb.append("\n   --- Itens do Negócio ---\n");
        for (ItemNegocio item : this.produtos) {
            sb.append(String.format("   - Produto: %-25s | Qtd: %-5d | Valor Unit.: R$ %.2f\n",
                    item.getProduto().getNome(),
                    item.getQtd(),
                    (tipo == TipoNegocio.VENDA ? item.getProduto().getValorVenda() : item.getProduto().getValorCompra())
            ));
        }

        sb.append(String.format("\n   VALOR TOTAL DA " + String.valueOf(tipo).toUpperCase() + ": R$ %.2f\n", this.valorNegocio));

        if (this.funcionariosEnvolvidos != null && !this.funcionariosEnvolvidos.isEmpty()) {
            sb.append("\n   --- Funcionários Envolvidos ---\n");
            for (String funcionario : funcionariosEnvolvidosToString()) {
                sb.append("   - ").append(funcionario).append("\n");
            }
        }

        sb.append("----------------------------------------\n");

        return sb.toString();
    }
}