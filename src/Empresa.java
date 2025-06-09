import exception.CodigoUnicoExistenteException;
import model.Caixa;
import model.Funcionario;
import model.Transportadora;

import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private Caixa caixa;
    private Transportadora transportadoras;
    private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

    public Empresa() {
        this.caixa = new Caixa(200000);
        this.transportadoras = new Transportadora();
    }

    public void addFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public void validarCodigoUnicoFuncionario(String codigoFuncionario) throws CodigoUnicoExistenteException {
        for(Funcionario funcionario: funcionarios) {
            if(codigoFuncionario.equals(funcionario.getCodigoFuncionario())) {
                throw new CodigoUnicoExistenteException("Código único já existente. Insira outro código único.");
            }
        }
    }

    public String exibirGeneros() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- Masculino  2- Feminino\n");
        return sb.toString();
    }

    public String exibirSetores() {
        StringBuilder sb = new StringBuilder();
        sb.append("1- Gerente de Filial  2- Atendimento ao Cliente  3- Gestão de Pessoas  4- Financeiro  5- Vendas  6- Almoxarifado\n");
        return sb.toString();
    }

    public void exibirFuncionarios() {
        if (funcionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        }
    }


    public Caixa getCaixa() {
        return caixa;
    }

    public Transportadora getTransportadoras() { return transportadoras; }
}