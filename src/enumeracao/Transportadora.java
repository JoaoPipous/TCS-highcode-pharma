package enumeracao;

public enum Transportadora {
    LONDRINA("Londrina", 100.0),
    CAMBE("Cambe", 200.0),
    ROLANDIA("Rolandia", 300.0);

    private String cidadeTransportadora;
    private Double valorFreteFixo;

    Transportadora(String cidadeTransportadora, Double valorFreteFixo) {
        this.cidadeTransportadora = cidadeTransportadora;
        this.valorFreteFixo = valorFreteFixo;
    }

    public String getCidadeTransportadora() { return cidadeTransportadora; }
    public Double getValorFreteFixo() { return valorFreteFixo; }
}
