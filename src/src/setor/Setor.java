package setor;

public class Setor {
    private static int qtdFuncionariosTotal = 0;
    private String nome;
    private int qtdFuncionarios;
    private int qtdLimite;

    public Setor(String nome, int qtdFuncionarios) {
        this.nome = nome;
        this.qtdFuncionarios = 0;
    }

    public void addQtdFuncionario() {
        qtdFuncionarios++;
        addQtdFuncionariosTotais();
    }

    public static int getQtdFuncionariosTotal() {
        return qtdFuncionariosTotal;
    }

    public static void setQtdFuncionariosTotal(int qtdFuncionariosTotal) {
        Setor.qtdFuncionariosTotal = qtdFuncionariosTotal;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
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