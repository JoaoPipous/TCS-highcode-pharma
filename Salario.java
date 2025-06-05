package model;

public class Salario {
    private double vale;
    private double planoSaude;
    private double planoOdontologico;
    private double bonusParticipacao;
    private double taxaAliquota;
    private double salarioBruto;

    public Salario(double vale, double planoSaude, double planoOdontologico, double bonusParticipacao, double salarioBruto) {
        this.vale = vale;
        this.planoSaude = planoSaude;
        this.planoOdontologico = planoOdontologico;
        this.bonusParticipacao = bonusParticipacao;
        this.salarioBruto = salarioBruto;
        setTaxaAliquota();
    }

    private void setTaxaAliquota() {
        if (salarioBruto <= 2428.80) {
            taxaAliquota = 0;
        } else if (salarioBruto > 2428.81 && salarioBruto <= 2826.65) {
            taxaAliquota = 0.075;
        } else if (salarioBruto > 2826.66 && salarioBruto <= 3751.05) {
            taxaAliquota = 0.15;
        } else if (salarioBruto > 3751.06 && salarioBruto <= 4664.68) {
            taxaAliquota = 0.225;
        } else {
            taxaAliquota = 0.275;
        }
    }

    public void calcularSalario(Funcionario funcionario){
        double desconto = planoOdontologico + planoSaude + bonusParticipacao + vale + (salarioBruto * taxaAliquota);
        double salarioLiquido = salarioBruto + bonusParticipacao - desconto;

        funcionario.setSalario(salarioLiquido);

        System.out.println("O salário líquido do funcionario é R$" + salarioLiquido);
    }

    @Override
    public String toString() {
        return "**Informações salariais**" + "\n" +
                "Vale Refeição/Alimentação: R$" + vale + "\n" +
                "Plano de saúde: R$" + planoSaude + "\n" +
                "Plano Odontológico: R$" + planoOdontologico + "\n" +
                "Bonus de participação: R$" + bonusParticipacao + "\n" +
                "Aliquota aplicada: " + taxaAliquota + "%" + "\n" +
                "Salario Bruto: R$" + salarioBruto;
    }
}
