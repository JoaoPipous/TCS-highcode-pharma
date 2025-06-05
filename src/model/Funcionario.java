package model;

import enumeracao.Genero;

public class Funcionario {
    private String nome;
    private String sobrenome;
    private String codigoFuncionario;
    private int idade;
    private Genero genero;
    private double salario;
    private Setor setor;

    public Funcionario(String nome, String sobrenome, String codigoFuncionario, int idade, Genero genero, Setor setor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.codigoFuncionario = codigoFuncionario;
        this.idade = idade;
        this.genero = genero;
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public int getIdade() {
        return idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public double getSalario() {
        return salario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}