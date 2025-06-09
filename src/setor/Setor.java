package setor;

public class Setor {
    protected static int qtdFuncionariosTotal = 0;
    private static int contador = 0;
    private String nome;
    private int qtdFuncionarios;

    public Setor(String nome) {
        this.nome = nome;
        qtdFuncionariosTotal = 0;
    }
    
    public Setor() {
        qtdFuncionariosTotal++;
    }

    public void addQtdFuncionario() {
        qtdFuncionarios++;
    }

    public static int getQtdFuncionariosTotal() {
        return qtdFuncionariosTotal;
    }

    public static void setQtdFuncionariosTotal(int qtdFuncionariosTotal) {
        Setor.qtdFuncionariosTotal = qtdFuncionariosTotal;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Setor.contador = contador;
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

    public String exibirSetor() {
        return "\nNome do setor: " + nome + "\n" +
                "Quantidade de funcionários:" + qtdFuncionarios + "\n";
    }
}