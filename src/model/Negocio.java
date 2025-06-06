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

    public Negocio(Status status, ArrayList<Funcionario> funcionariosEnvolvidos, ArrayList<ItemNegocio> itens, TipoNegocio tipo) {
        this.valorNegocio = calcularValorTotal();
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produtos = itens;
        this.tipo = tipo;
    }

    public Negocio(Status status, ArrayList<Funcionario> funcionariosEnvolvidos, ArrayList<ItemNegocio> itens, LocalDateTime dataProgramada, TipoNegocio tipo) {
        this.valorNegocio = calcularValorTotal();
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produtos = itens;
        this.dataProgramada = dataProgramada;
        this.tipo = tipo;
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

    public double getValorNegocio() {
        return valorNegocio;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Funcionario> getFuncionariosEnvolvidos() {
        return funcionariosEnvolvidos;
    }

    public ArrayList<ItemNegocio> getProduto() {
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
                soma += item.getProduto().getValorVenda();
            }
        } else {
            for(ItemNegocio item : produtos) {
                soma += item.getProduto().getValorCompra();
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
