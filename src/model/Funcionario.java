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
        this.qtdVendas = qtdVendas;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public String getCodigoFuncionario() {
        return codigoFuncionario;
    }
    public void setCodigoFuncionario(String codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public Genero getGenero() {
        return genero;
    }
    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
    public int getQtdVendas() {
        return qtdVendas;
    }
    public void setQtdVendas(int qtdVendas) {
        this.qtdVendas = qtdVendas;
    }
    public Setor getSetor() {
        return setor;
    }
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public void incrementarVendas(){
        this.qtdVendas++;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", codigoFuncionario='" + codigoFuncionario + '\'' +
                ", idade=" + idade +
                ", genero=" + genero +
                ", salario=" + salario +
                ", setor=" + setor +
                ", qtdVendas=" + qtdVendas +
                '}';
    }
}
