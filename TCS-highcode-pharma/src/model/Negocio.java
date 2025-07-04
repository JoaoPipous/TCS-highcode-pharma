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
    public Negocio(Status status, ArrayList<ItemNegocio> itens, LocalDateTime dataProgramada, TipoNegocio tipo) {
        this.status = status;
        // this.funcionariosEnvolvidos = funcionariosEnvolvidos;
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
        sb.append(tipo.toString() + " - Funcionários envolvidos:\n");

        for(String funcionario : funcionariosEnvolvidosToString()) {
            sb.append(funcionario + "\n");
        }

        return sb.toString();
    }
}
