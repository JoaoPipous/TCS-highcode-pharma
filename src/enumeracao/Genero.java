package enumeracao;

public enum Genero {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private final String genero;

    private Genero(String genero) {
        this.genero = genero;
    }

    public String toString(){
        return this.genero;
    }
}
