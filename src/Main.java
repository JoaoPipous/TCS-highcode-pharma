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

        empresa.getAlmoxarifado().criarProdutosIniciais();

        String[] nomesSetores = {"Almoxarifado", "Atendimento ao cliente", "Financeiro", "Gerente da filial", "Gestão de pessoas", "Vendas"};

        for (int i = 0; i < nomesSetores.length; i++) {
            empresa.criarSetor(nomesSetores[i]);
        }

        // *** TESTES ***

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
                    break;

                case 2:
                    break;

                case 3:
                    ArrayList<ItemNegocio> produtosCompra = new ArrayList<>();
                    tipoNegocio = TipoNegocio.COMPRA;
                    LocalDateTime dataHoraLida;

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
                                System.out.println("\n❌ Erro: O formato digitado está incorreto. Por favor, use o formato dd/MM/yyyy HH:mm.");
                            }
                        }
                    }

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
                    empresa.getAlmoxarifado().exibirProdutos();
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    break;

                case 10:
                    break;

                case 11:
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