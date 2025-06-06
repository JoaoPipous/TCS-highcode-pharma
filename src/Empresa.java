import model.Caixa;
import model.Transportadora;

public class Empresa {
    private Caixa caixa;
    private Transportadora transportadoras;

    public Empresa(){
        this.caixa = new Caixa(200000);
        this.transportadoras = new Transportadora();
    }

    public String exibirSetores() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- Gerente de Filial  2- Atendimento ao Cliente  3- Gestão de Pessoas  4- Financeiro  5- Vendas  6- Almoxarifado\n");
        return sb.toString();
    }

    public Caixa getCaixa() {
        return caixa;
    }

    public Transportadora getTransportadoras() { return transportadoras; }
}