package setor;

public class Setor {
    private static int qtdFuncionariosTotal = 0;
    private String nome;
    private int qtdFuncionarios;
    private int qtdLimite;

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

    public int getQtdLimite() {
        return qtdLimite;
    }

    public void setQtdLimite(int qtdLimite) {
        this.qtdLimite = qtdLimite;
    }
}