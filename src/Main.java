import exception.*;
import model.Funcionario;
import model.Produto;

import java.time.Year;
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

                    try {
                        System.out.println("Digite o nome do funcionário:");
                        String nomeFuncionario = sc.nextLine();

                        System.out.println("Digite o sobrenome do funcionário:");
                        String sobrenomeFuncionario = sc.nextLine();

                        System.out.println("Digite o código único do funcionário:");
                        String codigoFuncionario = sc.nextLine();

                        System.out.println("Digite a idade do funcionário:");
                        int idadeFuncionario = Integer.parseInt(sc.nextLine());

                        System.out.println(empresa.exibirGeneros());
                        System.out.println("Digite o número do gênero correspondente do funcionário:");
                        int numGenero = Integer.parseInt(sc.nextLine());

                        System.out.println(empresa.exibirSetores());
                        System.out.println("Digite o número do setor correspondente do funcionário:");
                        int numSetor = Integer.parseInt(sc.nextLine());

                        Funcionario funcionario = new Funcionario(nomeFuncionario, sobrenomeFuncionario, codigoFuncionario, idadeFuncionario, numGenero, numSetor);
                        empresa.addFuncionario(funcionario);
                        System.out.println("Funcionário adicionado com sucesso!");
                    } catch (GeneroInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (SetorInvalidoException e) {
                        System.out.println(e.getMessage());
                    } catch (QuantidadeLimiteFuncionariosException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                }

                case 2: {

                    System.out.println("Digite o nome do produto:");
                    String nomeProduto = sc.nextLine();

                    System.out.println("Digite o valor de compra do produto:");
                    double valorCompra = Double.parseDouble(sc.nextLine());

                    System.out.println("Digite o valor de venda do produto:");
                    double valorVenda = Double.parseDouble(sc.nextLine());

                    System.out.println("Digite a quantidade em estoque atual do produto:");
                    int qtdEstoque = Integer.parseInt(sc.nextLine());

                    System.out.println("Escolha a categoria do produto:");
                    System.out.println("1- Medicamento  2- Higiene  3- Cosmético  4- Alimentício");
                    int categoria = Integer.parseInt(sc.nextLine());

                    try {
                        Produto produto = new Produto(nomeProduto, valorCompra, valorVenda, qtdEstoque, categoria);
                    } catch(CategoriaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }

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

                    System.out.println("Lista de produtos:\n");
                    empresa.getCaixa().exibirProdutos();
                    break;
                }

                case 7: {

                    System.out.println("Lista de compras:\n");
                    empresa.getCaixa().exibirCompras();
                    break;
                }

                case 8: {

                    System.out.println("Lista de vendas:\n");
                    empresa.getCaixa().exibirVendas();
                    break;
                }

                case 9: {

                    System.out.println("Transportadoras:\n");
                    empresa.getTransportadoras().exibirTransportadora();
                    break;
                }

                case 10: {

                    System.out.println("Para verificar a estimativa mensal, digite o número referente ao mês desejado: (1 a 12)");
                    int mensal = Integer.parseInt(sc.nextLine());

                    System.out.println("Para verificar a estimativa anual, digite o ano desejado:");
                    int anual = Integer.parseInt(sc.nextLine());

                    System.out.printf("Valor total do caixa: R$%.2f\nEstimativa mensal: R$%.2f\nEstimativa anual: R$%.2f\n", empresa.getCaixa().getValorTotal(), empresa.getCaixa().estimarLucroMensal(mensal), empresa.getCaixa().estimarLucroAnual(anual));
                    break;
                }

                case 11: {

                    System.out.println("Negócios em aberto:\n");
                    empresa.getCaixa().exibirNegociosAbertos();
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