package enumeracao;

public enum TipoNegocio {
    COMPRA("Compra"),
    VENDA("Venda");

    private final String tipoNegocio;

    private TipoNegocio(String tipoNegocio) {
        this.tipoNegocio = tipoNegocio;
    }

    public String toString(){
        return this.tipoNegocio;
    }
}
