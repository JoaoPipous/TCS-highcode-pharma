package model;

import enumeracao.Status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Negocio {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
    private final LocalDateTime dataNegocio = LocalDateTime.now();
    private double valorNegocio;
    private Status status;
    private ArrayList<Funcionario> funcionariosEnvolvidos;
    private Produto produto;
    private LocalDateTime dataProgramada;

    public Negocio(double valorNegocio, Status status, ArrayList<Funcionario> funcionariosEnvolvidos, Produto produto) {
        this.valorNegocio = valorNegocio;
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produto = produto;
    }

    public Negocio(double valorNegocio, Status status, ArrayList<Funcionario> funcionariosEnvolvidos, Produto produto, LocalDateTime dataProgramada) {
        this.valorNegocio = valorNegocio;
        this.status = status;
        this.funcionariosEnvolvidos = funcionariosEnvolvidos;
        this.produto = produto;
        this.dataProgramada = dataProgramada;
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

    public Produto getProduto() {
        return produto;
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

    public String exibirDados() {
        return "";
    }
}
