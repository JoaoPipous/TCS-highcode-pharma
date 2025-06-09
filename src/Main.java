import enumeracao.Status;
import enumeracao.TipoNegocio;
import exception.ProdutoNaoEncontradoException;
import model.Funcionario;
import model.ItemNegocio;
import model.Negocio;
import model.Produto;
import setor.Setor;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.*;
import model.Funcionario;
import model.Produto;

import java.time.Year;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static TipoNegocio tipoNegocio;
    static Status status;
    static Negocio negocio;
    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();

        // *** TESTES ***

        try {
            empresa.getAlmoxarifado().criarProdutosIniciais();
        } catch(CategoriaInvalidaException e) {
            System.out.println("\n" + e.getMessage());
        }

        String[] nomesSetores = {"Almoxarifado", "Atendimento ao cliente", "Financeiro", "Gerente da filial", "Gestão de pessoas", "Vendas"};

        for (int i = 0; i < nomesSetores.length; i++) {
            empresa.criarSetor(nomesSetores[i]);
        }

        // *** TESTES ***

        empresa.getAlmoxarifado().exibirProdutos();

        while (true) {

            // *** Vendas e compras adicionadas quando Status = FINALIZADO
            // Caso a venda esteja programada ainda falta implementar datas ***

            System.out.println("\nEscolha uma opção:");
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
            System.out.println("12 - Exibir setores da empresa");
            System.out.println("13 - Sair");

            int opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1:

                    System.out.println("Digite o nome do funcionário:");
                    String nomeFuncionario = sc.nextLine();

                    System.out.println("Digite o sobrenome do funcionário:");
                    String sobrenomeFuncionario = sc.nextLine();

                    /* try {
                        System.out.println("Digite o código único do funcionário:");
                        String codigoFuncionario = sc.nextLine();
                    } catch(CodigoUnicoExistenteException e) {
                        System.out.println(e.getMessage());
                    } */

                    System.out.println("Digite a idade do funcionário:");
                    int idadeFuncionario = Integer.parseInt(sc.nextLine());

                    System.out.println(empresa.exibirGeneros());
                    System.out.println("Digite o número do gênero correspondente do funcionário:");
                    int numGenero = Integer.parseInt(sc.nextLine());

                    System.out.println(empresa.exibirSetores());
                    System.out.println("Digite o número do setor correspondente do funcionário:");
                    int numSetor = Integer.parseInt(sc.nextLine());

//                    try {
//                        Funcionario funcionario = new Funcionario(nomeFuncionario, sobrenomeFuncionario, codigoFuncionario, idadeFuncionario, numGenero, numSetor);
//                        empresa.addFuncionario(funcionario);
//                        System.out.println("Funcionário adicionado com sucesso!");
//                    } catch (GeneroInvalidoException e) {
//                        System.out.println(e.getMessage());
//                    } catch (SetorInvalidoException e) {
//                        System.out.println(e.getMessage());
//                    } catch (QuantidadeLimiteFuncionariosException e) {
//                        System.out.println(e.getMessage());
//                    }

                    break;
                  
                 case 2:

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
                        empresa.getAlmoxarifado().adicionarProduto(produto);
                        System.out.println("Produto adicionado com sucesso!");
                    } catch(CategoriaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 3:
                    ArrayList<ItemNegocio> produtosCompra = new ArrayList<>();
                    tipoNegocio = TipoNegocio.COMPRA;
                    LocalDateTime dataHoraLida = LocalDateTime.now();

                    System.out.println("\nQual o status da compra?");
                    System.out.println("1 - Em aberto");
                    System.out.println("2 - Finalizada");

                    opcao = Integer.parseInt(sc.nextLine());

                    status = (opcao == 1 ? Status.ABERTO : Status.FINALIZADO);

                    while(true) {
                        if(status.equals(Status.ABERTO)) {
                            System.out.println("\nInsira a data de finalização da compra, formato: dd/MM/yyyy hh:mm:ss");
                            try {
                                String data = sc.nextLine();
                                dataHoraLida = LocalDateTime.parse(data, formatador);
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("\nErro: O formato digitado está incorreto. Por favor, use o formato dd/MM/yyyy HH:mm.");
                            }
                        } else {
                            break;
                        }
                    }

                    while (true) {
                        System.out.println("\nEscolha o produto que deseja comprar: \n");

                        int contador = 1;

                        for (Produto p : empresa.getAlmoxarifado().getProdutos()) {
                            System.out.println(contador + " - " + p.exibirInformacoes());
                            contador++;
                        }

                        System.out.println(contador + " - Sair.");

                        int produto = Integer.parseInt(sc.nextLine());

                        if (produto == contador) {
                            break;
                        }

                        System.out.println("\nInforme a quantidade que deseja comprar: ");
                        int quantidadeProduto = Integer.parseInt(sc.nextLine());

                        if (produto >= 1 && produto <= empresa.getAlmoxarifado().getProdutos().size()) {
                            produtosCompra.add(new ItemNegocio(empresa.getAlmoxarifado().getProdutos().get(produto - 1), quantidadeProduto));
                        } else {
                            System.out.println("\nProduto não encontrado.");
                        }
                    }

                    if(status.equals(Status.FINALIZADO)) {
                        negocio = new Negocio(status, produtosCompra, TipoNegocio.COMPRA);
                    } else {
                        negocio = new Negocio(status, produtosCompra, dataHoraLida, TipoNegocio.COMPRA);
                    }

                    empresa.registrarCompra(negocio);        
                   
                   break;

                case 4:
                    ArrayList<ItemNegocio> produtosVenda = new ArrayList<>();
                    tipoNegocio = TipoNegocio.COMPRA;

                    System.out.println("\nQual o status da compra?");
                    System.out.println("1 - Em aberto");
                    System.out.println("2 - Finalizada");

                    opcao = Integer.parseInt(sc.nextLine());

                    status = (opcao == 1 ? Status.ABERTO : Status.FINALIZADO);

                    while (true) {
                        System.out.println("\nEscolha o produto que deseja comprar: ");

                        int contador = 1;


                        for (Produto p : empresa.getAlmoxarifado().getProdutos()) {
                            System.out.println(contador + " - " + p.exibirInformacoes());
                            contador++;
                        }

                        System.out.println(contador + " - Sair.");

                        int produto = Integer.parseInt(sc.nextLine());

                        if (produto == contador) {
                            break;
                        }

                        int quantidadeProduto = Integer.parseInt(sc.nextLine());

                        if (produto >= 1 && produto <= empresa.getAlmoxarifado().getProdutos().size()) {
                            produtosVenda.add(new ItemNegocio(empresa.getAlmoxarifado().getProdutos().get(produto - 1), quantidadeProduto));
                        } else {
                            System.out.println("\nProduto não encontrado.");
                        }
                    }

                    negocio = new Negocio(status, produtosVenda, TipoNegocio.VENDA);
                    empresa.registrarVenda(negocio);
                    break;
                  
                case 5:
                    break;
                  
                case 6:
                    System.out.println("Lista de produtos:\n");
                    empresa.getAlmoxarifado().exibirProdutos();
                    break;
                  
                case 7:
                    System.out.println("Lista de compras:\n");
                    empresa.getCaixa().exibirCompras();
                    break;

                case 8:
                    System.out.println("Lista de vendas:\n");
                    empresa.getCaixa().exibirVendas();
                    break;

                case 9:
                    System.out.println("Transportadoras:\n");
                    empresa.getTransportadoras().exibirTransportadora();
                    break;

                case 10:
                    System.out.println("Para verificar a estimativa mensal, digite o número referente ao mês desejado: (1 a 12)");
                    int mensal = Integer.parseInt(sc.nextLine());

                    System.out.println("Para verificar a estimativa anual, digite o ano desejado:");
                    int anual = Integer.parseInt(sc.nextLine());

                    System.out.printf("Valor total do caixa: R$%.2f\nEstimativa mensal: R$%.2f\nEstimativa anual: R$%.2f\n", empresa.getCaixa().getValorTotal(), empresa.getCaixa().estimarLucroMensal(mensal), empresa.getCaixa().estimarLucroAnual(anual));
                    break;
                  
                  case 11:
                    System.out.println("Negócios em aberto:\n");
                    empresa.getCaixa().exibirNegociosAbertos();
                    break;

                case 12:
                    System.out.println("\nExibindo setores:");

                    for (Setor setor : empresa.getSetores()) {
                        System.out.println(setor.exibirSetor());
                    }
                    break;       

                case 13:
                    sc.close();
                    return;

                default:
                    System.out.println("Entrada inválida!");
                    break;
            }
        }
    }
}