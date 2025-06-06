package setor;

public class Setor {
    private static int qtdFuncionariosTotal = 0;
    private String nome;
    private int qtdFuncionarios = 0;
    private int qtdLimite;

    public Setor(String nome) {
        this.nome = nome;
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

    public String exibirSetor() {
        return "\nNome do setor: " + nome + "\n" +
                "Quantidade de funcionários:" + qtdFuncionarios + "\n";
    }
}