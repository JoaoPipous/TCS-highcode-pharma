package model;

import setor.Setor;

public class Salario {
    private double vale;
    private double planoSaude;
    private double planoOdontologico;
    private double bonusParticipacao;
    private double taxaAliquota;
    private double salarioBruto;

    public Salario(double vale, double bonusParticipacao, double salarioBruto, Funcionario funcionario) {
        this.vale = vale;
        setPlanoSaude(funcionario);
        this.planoOdontologico = 3000;
        this.bonusParticipacao = bonusParticipacao;
        this.salarioBruto = salarioBruto;
        setTaxaAliquota();
    }

    private void setPlanoSaude(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();

        if (setor == null || setor.getNome() == null) {
            planoSaude = 0;
            return;
        }

        String nomeSetor = setor.getNome();

        if (nomeSetor.equalsIgnoreCase("Vendas") || nomeSetor.equalsIgnoreCase("Atendimento ao Cliente")) {
            planoSaude = 3000;
        } else if (nomeSetor.equalsIgnoreCase("Almoxarifado")) {
            planoSaude = 3500;
        } else if (nomeSetor.equalsIgnoreCase("Financeiro")) {
            planoSaude = 3750;
        } else if (nomeSetor.equalsIgnoreCase("GestaoPessoas")) {
            planoSaude = 4200;
        } else if (nomeSetor.equalsIgnoreCase("GerenteFilial")) {
            planoSaude = 5000;
        }
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

    public void calcularSalario(model.Funcionario funcionario) {
        double desconto =  vale + (salarioBruto * taxaAliquota);

        double salarioLiquido = salarioBruto + bonusParticipacao - desconto;

        funcionario.setSalario(salarioLiquido);

        System.out.println("O salário líquido do funcionario é R$" + salarioLiquido + "\n");
    }

    @Override
    public String toString() {
        return "**Informações salariais**" + "\n" +
                "Vale Refeição/Alimentação: R$" + vale + "\n" +
                "Cobertura do plano de saúde: R$" + planoSaude + "\n" +
                "Cobertura do plano Odontológico: R$" + planoOdontologico + "\n" +
                "Bonus de participação: R$" + bonusParticipacao + "\n" +
                "Aliquota aplicada: " + (taxaAliquota * 100) + "%" + "\n" +
                "Salario Bruto: R$" + salarioBruto;
    }
}