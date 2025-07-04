package model;

import enumeracao.Genero;
import exception.CodigoUnicoExistenteException;
import exception.GeneroInvalidoException;
import exception.QuantidadeLimiteFuncionariosException;
import exception.SetorInvalidoException;
import setor.*;

public class Funcionario {
    private String nome;
    private String sobrenome;
    private String codigoFuncionario;
    private int idade;
    private Genero genero;
    private double salario;
    private Setor setor;
    private int qtdVendas;

    public Funcionario(String nome, String sobrenome, String codigoFuncionario, int idade, int genero, Setor setor) throws GeneroInvalidoException, SetorInvalidoException, QuantidadeLimiteFuncionariosException, CodigoUnicoExistenteException {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.codigoFuncionario = codigoFuncionario;
        this.idade = idade;

        if(definirGenero(genero) == null) {
            throw new exception.GeneroInvalidoException("Entrada inválida: gênero não encontrado.");
        } else {
            this.genero = definirGenero(genero);
        }

        this.setor = setor;

        this.qtdVendas = 0;
    }

    public Genero definirGenero(int genero) {
        switch (genero) {
            case 1: return Genero.MASCULINO;
            case 2: return Genero.FEMININO;
        }

        return null;
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
