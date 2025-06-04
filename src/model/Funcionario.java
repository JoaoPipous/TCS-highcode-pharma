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
    private int qtdVendas;

    public Funcionario(String nome, String sobrenome, String codigoFuncionario, int idade, Genero genero, Setor setor) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.codigoFuncionario = codigoFuncionario;
        this.idade = idade;
        this.genero = genero;
        this.setor = setor;
    }
}