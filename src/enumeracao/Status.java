package enumeracao;

public enum Status {
    ABERTO("Aberto"),
    FINALIZADO("Finalizado");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String toString(){
        return this.status;
    }
}
