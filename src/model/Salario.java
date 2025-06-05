package model;

public class Salario {
    private double vale;
    private double planoSaude;
    private double planoOdontologico;
    private double bonusParticipacao;
    private double taxaAliquota;
    private double salarioBruto;
    private final double desconto = planoOdontologico + planoSaude + bonusParticipacao + vale + (salarioBruto * taxaAliquota);
    private final double salarioLiquido = salarioBruto + bonusParticipacao - desconto;

    public Salario(double vale, double planoSaude, double planoOdontologico, double bonusParticipacao, double salarioBruto) {
        this.vale = vale;
        this.planoSaude = planoSaude;
        this.planoOdontologico = planoOdontologico;
        this.bonusParticipacao = bonusParticipacao;
        setTaxaAliquota(taxaAliquota);
        this.salarioBruto = salarioBruto;
    }

    public double getVale() {
        return vale;
    }

    public void setVale(double vale) {
        this.vale = vale;
    }

    public double getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(double planoSaude) {
        this.planoSaude = planoSaude;
    }

    public double getPlanoOdontologico() {
        return planoOdontologico;
    }

    public void setPlanoOdontologico(double planoOdontologico) {
        this.planoOdontologico = planoOdontologico;
    }

    public double getBonusParticipacao() {
        return bonusParticipacao;
    }

    public void setBonusParticipacao(double bonusParticipacao) {
        this.bonusParticipacao = bonusParticipacao;
    }

    public double getTaxaAliquota() {
        return taxaAliquota;
    }

    public void setTaxaAliquota(double taxaAliquota) {
        if (salarioBruto <= 2428.80) {
            taxaAliquota = 0;
        } else if (salarioBruto > 2428.81 && salarioBruto <= 2826.65) {
            taxaAliquota = 7.5;
        } else if (salarioBruto > 2826.66 && salarioBruto <= 3751.05) {
            taxaAliquota = 15;
        } else if (salarioBruto > 3751.06 && salarioBruto <= 4664.68) {
            taxaAliquota = 22.5;
        } else {
            taxaAliquota = 27.5;
        }
    }

    public void calcularSalario(){
        Funcionario.setSalario(salarioLiquido);
    }

}
