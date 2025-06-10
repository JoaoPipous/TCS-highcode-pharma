package setor;

public class Setor {
    protected int qtdFuncionariosTotal = 0;
    private int contador = 0;
    private String nome;
    private int qtdFuncionarios = 0;
  
    public Setor() {}

    public void addQtdFuncionario() {
        qtdFuncionarios++;
    }

    public int getQtdFuncionariosTotal() {
        return qtdFuncionariosTotal;
    }

    public void setQtdFuncionariosTotal(int qtdFuncionariosTotal) {
        this.qtdFuncionariosTotal = qtdFuncionariosTotal;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
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
                "Quantidade de funcionários: " + qtdFuncionarios + "\n";
    }
}