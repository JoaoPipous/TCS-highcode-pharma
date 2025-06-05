import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();

        empresa.getCaixa().exibirProdutos();

        while(true) {

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar funcionário");
            System.out.println("2 - Adicionar produto");
            System.out.println("3 - Realizar uma compra");
            System.out.println("4 - Realizar uma venda");
            System.out.println("5 - Listar funcionários");
            System.out.println("6 - Listar produtos");
            System.out.println("7 - Listar compras");
            System.out.println("8 - Listar vendas");
            System.out.println("9 - Exibir transportadoras");
            System.out.println("10 - Mostrar o valor total do caixa da empresa e estimativa de lucros");
            System.out.println("11 - Exibir negócios em aberto");
            System.out.println("12 - Sair");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {

                case 1: {
                    break;
                }

                case 2: {
                    break;
                }

                case 3: {
                    break;
                }

                case 4: {
                    break;
                }

                case 5: {
                    break;
                }

                case 6: {
                    break;
                }

                case 7: {
                    break;
                }

                case 8: {
                    break;
                }

                case 9: {
                    break;
                }

                case 10: {
                    break;
                }

                case 11: {
                    break;
                }

                case 12: {
                    sc.close();
                    return;
                }

                default: {
                    System.out.println("Entrada inválida!");
                    break;
                }

            }

        }

    }
}