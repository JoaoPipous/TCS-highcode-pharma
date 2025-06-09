package model;

import setor.Setor;

public class Salario {
    private double vale;
    private double planoSaude;
    private double planoOdontologico;
    private double bonusParticipacao;
    private double taxaAliquota;
    private double salarioBruto;

    public Salario(double bonusParticipacao,double salarioBruto, Funcionario funcionario) {
        this.bonusParticipacao = bonusParticipacao;
        setVale(funcionario);
        setPlanoSaude(funcionario);
        this.planoOdontologico = 3000;
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
        } else if (nomeSetor.equalsIgnoreCase("Gestão de Pessoas")) {
            planoSaude = 4200;
        } else if (nomeSetor.equalsIgnoreCase("Gerente de Filial")) {
            planoSaude = 5000;
        }
    }

    private void setVale(Funcionario funcionario) {
        Setor setor = funcionario.getSetor();

        if (setor == null || setor.getNome() == null) {
            vale = 300;
            return;
        }

        String nomeSetor = setor.getNome();

        if (nomeSetor.equalsIgnoreCase("Vendas") || nomeSetor.equalsIgnoreCase("Atendimento ao Cliente")) {
            vale = 300;
        } else if (nomeSetor.equalsIgnoreCase("Almoxarifado")) {
            vale = 360;
        } else if (nomeSetor.equalsIgnoreCase("Financeiro")) {
            vale = 400;
        } else if (nomeSetor.equalsIgnoreCase("Gestão de Pessoas")) {
            vale = 520;
        } else if (nomeSetor.equalsIgnoreCase("Gerente de Filial")) {
            vale = 1000;
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

    public void calcularSalario(Funcionario funcionario) {
        double desconto = vale + (salarioBruto * taxaAliquota);

        double salarioLiquido = salarioBruto + bonusParticipacao - desconto;

        funcionario.setSalario(salarioLiquido);

        System.out.println("Salario calculado e definido: R$" + salarioLiquido + "\n");
    }

    @Override
    public String toString() {
        return "**Informações salariais**" + "\n" +
                "--------------------------------------------" + "\n" +
                "Vale Refeição/Alimentação: R$" + String.format("%.2f", vale) + "\n" +
                "Cobertura do plano de saúde: R$" + String.format("%.2f/Mês", planoSaude) + "\n" +
                "Cobertura do plano Odontológico: R$" + String.format("%.2f/Mês", planoOdontologico) + "\n" +
                "Bonus de participação: R$" + String.format("%.2f", bonusParticipacao) + "\n" +
                "Aliquota aplicada: " + String.format("%.2f", (taxaAliquota * 100)) + "%" + "\n" +
                "Salario Bruto: R$" + String.format("%.2f", salarioBruto) + "\n" +
                "--------------------------------------------" + "\n";

    }
}