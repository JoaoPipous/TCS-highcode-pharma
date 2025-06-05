package model;

public class Setor {
    private static int qtdFuncionariosTotal = 0;
    private String nome;
    private int qtdFuncionarios;

    public Setor(String nome) {
        this.nome = nome;
        this.qtdFuncionarios = 0;
    }

    public void addQtdFuncionario() {
        qtdFuncionarios++;
        addQtdFuncionariosTotais();
    }

    public static void addQtdFuncionariosTotais() {
        qtdFuncionariosTotal++;
    }

    public static int getQtdFuncionariosTotais() {
        return qtdFuncionariosTotal;
    }
}