import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();

        empresa.getCaixa().exibirProdutos();

        while (true) {

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
            System.out.println("10 - Setar Plano de Saude e Odontologico");
            System.out.println("11 - Mostrar o valor total do caixa da empresa e estimativa de lucros");
            System.out.println("12 - Exibir negócios em aberto");
            System.out.println("13 - Sair");

            int opcao = sc.nextInt();

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
                    int planos;
                     do{
                        System.out.println("\n--- PLANOS DE VIDA ---");
                        System.out.println("1 - Informar gastos com plano de saude e odontologico");
                        System.out.println("0 - Voltar ao menu principal");
                        System.out.print("Escolha: ");
                        planos = sc.nextInt();

                        switch (planos) {
                            case 1:
                                System.out.print("Digite o valor gasto com plano de saude: ");
                                double planoSaude = sc.nextDouble();
                                System.out.print("Digite o valor gasto com plano odontologico: ");
                                double planoOdonto = sc.nextDouble();


                            case 0:
                                System.out.println("Voltando ao menu principal...");
                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }
                    }while(planos != 0);
                }


                case 11:{
                    break;
                }

                case 12: {
                    break;
                }

                case 13 : {
                    System.out.printf("\n--- SAINDO DO PROGRAMA ATÉ BREVE---\n");
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
