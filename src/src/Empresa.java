import model.Caixa;

public class Empresa {
    private Caixa caixa;

    public Empresa(){
        this.caixa = new Caixa(200000);
    }

    public Caixa getCaixa() {
        return caixa;
    }
}