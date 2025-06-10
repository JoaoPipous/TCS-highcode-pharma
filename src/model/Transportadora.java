package model;

public class Transportadora {

    private int qtdParceiras;
    private double valorFrete;
    private double toneladas;
    private String transportadoraEscolhida;

    private enum Local {

        LONDRINA("Londrina", 1000.0),
        CAMBE("CAMBE", 2000.0),
        ROLANDIA("Rolandia", 3000.0);

        private String cidadeTransportadora;
        private double valorFreteFixo;

        Local(String cidadeTransportadora, double valorFreteFixo) {
            this.cidadeTransportadora = cidadeTransportadora;
            this.valorFreteFixo = valorFreteFixo;
        }

        public static Local buscarcidadeTransportadora(String cidadeTransportadora) {
            for (Local l : Local.values()) {
                if (l.getCidadeTransportadora().equalsIgnoreCase(cidadeTransportadora)) {
                    return l;
                }
            }
            return null;
        }

        public String getCidadeTransportadora() {
            return cidadeTransportadora;
        }

        public String getLocal() {
            return cidadeTransportadora;
        }

        public double getValorFreteFixo() {
            return valorFreteFixo;
        }

    }

    public Transportadora() {
        qtdParceiras = 3;
    }

    public void calcularFrete(String transportadoraEscolhida, double toneladas) {
        Local local = Local.buscarcidadeTransportadora(transportadoraEscolhida);

        if (local != null) {
            double valorFinal;
            double valorExtra = 200;
            valorFinal = valorExtra * toneladas;
            System.out.println("O frete fixo para " + local.getCidadeTransportadora() + " é: R$ " + local.getValorFreteFixo());
            System.out.println("O valor final do frete ficou: " + (valorFinal + local.getValorFreteFixo()));
        } else {
            System.out.println("Transportadora não encontrada nesta cidade");
        }
    }

    public void exibirTransportadora(){
        System.out.println("Possuimos: " + qtdParceiras + " Transportadoras" );
        System.out.print("Cidades disponíveis: ");
        for(Local local: Local.values()){
            System.out.print(local.getCidadeTransportadora() + " ");
        }
    }
}