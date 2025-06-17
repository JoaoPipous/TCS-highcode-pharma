package enumeracao;

public enum Categoria {
    MEDICAMENTO("Medicamento"),
    HIGIENE("Higiene"),
    COSMETICO("Cosmético"),
    ALIMENTICIO("Alimentício");

    private final String categoria;

    private Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String toString(){
        return this.categoria;
    }
}