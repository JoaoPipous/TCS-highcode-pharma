package model;

public class Salario {
    private double vale;
    private double planoSaude;
    private double planoOdontologico;
    private double bonusParticipacao;
    private double taxaAliquota;

    public Salario(double vale, double planoSaude, double planoOdontologico, double bonusParticipacao, double taxaAliquota) {
        this.vale = vale;
        this.planoSaude = planoSaude;
        this.planoOdontologico = planoOdontologico;
        this.bonusParticipacao = bonusParticipacao;
        this.taxaAliquota = taxaAliquota;
    }
}
