import enumeracao.Status;
import enumeracao.TipoNegocio;
import exception.ProdutoNaoEncontradoException;
import model.Funcionario;
import model.ItemNegocio;
import model.Negocio;
import model.Produto;
import setor.Almoxarifado;
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
    static LocalDateTime dataHoraLida = LocalDateTime.now();
    static TipoNegocio tipoNegocio;
    static Status status;
    static Negocio negocio;
    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static void main(String[] args) throws CategoriaInvalidaException {

        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();

        empresa.getAlmoxarifado().criarProdutosIniciais();
        System.out.println("Produtos iniciais:");
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

                    try {
                        Setor setorDefinido = empresa.definirSetor(numSetor);
                        Funcionario funcionario = new Funcionario(nomeFuncionario, sobrenomeFuncionario, codigoFuncionario, idadeFuncionario, numGenero, setorDefinido);
                        empresa.validarCodigoUnicoFuncionario(codigoFuncionario);
                        empresa.addFuncionario(funcionario);
                        System.out.println("Funcionário adicionado com sucesso!");
                    } catch (CodigoUnicoExistenteException e) {
                        System.out.println(e.getMessage());
                    } catch (GeneroInvalidoException | SetorInvalidoException |
                             QuantidadeLimiteFuncionariosException e) {
                        System.out.println(e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

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
                    } catch (CategoriaInvalidaException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    ArrayList<ItemNegocio> produtosCompra = new ArrayList<>();
                    tipoNegocio = TipoNegocio.COMPRA;

                    System.out.println("\nQual o status da compra?");
                    System.out.println("1 - Em aberto");
                    System.out.println("2 - Finalizada");

                    opcao = Integer.parseInt(sc.nextLine());

                    // Ternária - caso opção for = 1 o status é ABERTO
                    // Caso contrário é FINALIZADO
                    status = (opcao == 1 ? Status.ABERTO : Status.FINALIZADO);

                    // Fica em loop (pedindo a data) até a verificação estar satisfeita
                    while (true) {
                        // Se o status for ABERTO, pede a data de finalização do negócio
                        if (status.equals(Status.ABERTO)) {
                            System.out.println("\nInsira a data de finalização da compra, formato: dd/MM/yyyy hh:mm:ss");
                            // Faz 2 verificação com try catch
                            // A primeira se a data digitada está no formato certo dd/MM/yyyy HH:MM/ss
                            // (Dia/mês/ano Hora/minutos/segundos)
                            // A segunda verifica se a data é válida, deve ser após a data atual
                            try {
                                String data = sc.nextLine();
                                dataHoraLida = LocalDateTime.parse(data, formatador);
                                if (dataHoraLida.isBefore(LocalDateTime.now())) {
                                    throw new DataValidaException("\nA data de finalização deve ser após a data atual.");
                                }
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("\nErro: O formato digitado está incorreto. Por favor, use o formato dd/MM/yyyy HH:mm.");
                            } catch (DataValidaException e) {
                                System.out.println("\n" + e.getMessage());
                            }
                        } else {
                            break;
                        }
                    }

                    // Loop até o usuário escolher sair
                    while (true) {
                        System.out.println("\nEscolha o(s) produto da compra: \n");

                        int contador = 1;

                        // Exibe todos os produtos do Almoxarifado (estoque)
                        for (Produto p : empresa.getAlmoxarifado().getProdutos()) {
                            System.out.println(contador + " - " + p.exibirInformacoes());
                            contador++;
                        }

                        System.out.println(contador + " - Sair.");

                        int produto = Integer.parseInt(sc.nextLine());

                        // Se a opção for igual o contador ele sai do loop
                        // No final da exibição, o contador é igual a opção de sair
                        if (produto == contador) {
                            break;
                        }

                        System.out.println("\nInforme a quantidade: ");
                        int quantidadeProduto = Integer.parseInt(sc.nextLine());

                        // Verifica se o produto existe
                        // O índice deve ser maior que 1 (pois a exibição de escolha começa em 1)
                        // E o índice deve ser menor ou igual que o tamanho da lista de produtos
                        try {
                            if (produto >= 1 && produto <= empresa.getAlmoxarifado().getProdutos().size()) {
                                produtosCompra.add(new ItemNegocio(empresa.getAlmoxarifado().getProdutos().get(produto - 1), quantidadeProduto));
                            } else {
                                throw new ProdutoNaoEncontradoException("\nProduto não encontrado.");
                            }
                        } catch (ProdutoNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Adiciona um negócio ao Caixa dependendo do status
                    if (status.equals(Status.FINALIZADO)) {
                        negocio = new Negocio(status, produtosCompra, TipoNegocio.COMPRA);
                    } else {
                        negocio = new Negocio(status, produtosCompra, dataHoraLida, TipoNegocio.COMPRA);
                    }

                    empresa.registrarCompra(negocio);

                    break;

                case 4:
                    ArrayList<ItemNegocio> produtosVenda = new ArrayList<>();
                    tipoNegocio = TipoNegocio.COMPRA;

                    System.out.println("\nQual o status da venda?");
                    System.out.println("1 - Em aberto");
                    System.out.println("2 - Finalizada");

                    opcao = Integer.parseInt(sc.nextLine());

                    status = (opcao == 1 ? Status.ABERTO : Status.FINALIZADO);

                    // Fica em loop (pedindo a data) até a verificação estar satisfeita
                    while (true) {
                        // Se o status for ABERTO, pede a data de finalização do negócio
                        if (status.equals(Status.ABERTO)) {
                            System.out.println("\nInsira a data de finalização da venda, formato: dd/MM/yyyy hh:mm:ss");
                            // Faz 2 verificação com try catch
                            // A primeira se a data digitada está no formato certo dd/MM/yyyy HH:MM/ss
                            // (Dia/mês/ano Hora/minutos/segundos)
                            // A segunda verifica se a data é válida, deve ser após a data atual
                            try {
                                String data = sc.nextLine();
                                dataHoraLida = LocalDateTime.parse(data, formatador);
                                if (dataHoraLida.isBefore(LocalDateTime.now())) {
                                    throw new DataValidaException("\nA data de finalização deve ser após a data atual.");
                                }
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("\nErro: O formato digitado está incorreto. Por favor, use o formato dd/MM/yyyy HH:mm.");
                            } catch (DataValidaException e) {
                                System.out.println("\n" + e.getMessage());
                            }
                        } else {
                            break;
                        }
                    }

                    // Loop até o usuário escolher sair
                    while (true) {
                        System.out.println("\nEscolha o produto da venda: \n");

                        int contador = 1;

                        // Exibe todos os produtos do Almoxarifado (estoque)
                        for (Produto p : empresa.getAlmoxarifado().getProdutos()) {
                            System.out.println(contador + " - " + p.exibirInformacoes());
                            contador++;
                        }

                        System.out.println(contador + " - Sair.");

                        int produto = Integer.parseInt(sc.nextLine());

                        // Se a opção for igual o contador ele sai do loop
                        // No final da exibição, o contador é igual a opção de sair
                        if (produto == contador) {
                            break;
                        }

                        System.out.println("\nInforme a quantidade: ");
                        int quantidadeProduto = Integer.parseInt(sc.nextLine());

                        // Verifica se o produto existe
                        // O índice deve ser maior que 1 (pois a exibição de escolha começa em 1)
                        // E o índice deve ser menor ou igual que o tamanho da lista de produtos
                        try {
                            if (produto >= 1 && produto <= empresa.getAlmoxarifado().getProdutos().size()) {
                                produtosVenda.add(new ItemNegocio(empresa.getAlmoxarifado().getProdutos().get(produto - 1), quantidadeProduto));
                            } else {
                                throw new ProdutoNaoEncontradoException("\nProduto não encontrado.");
                            }
                        } catch (ProdutoNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Adiciona um negócio ao Caixa dependendo do status
                    if (status.equals(Status.FINALIZADO)) {
                        negocio = new Negocio(status, produtosVenda, TipoNegocio.VENDA);
                    } else {
                        negocio = new Negocio(status, produtosVenda, dataHoraLida, TipoNegocio.VENDA);
                    }

                    try {
                        empresa.registrarVenda(negocio);
                    } catch (EstoqueInsuficienteException e) {
                        System.out.println("\n" + e.getMessage());
                    }

                    break;

                case 5:
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("Lista de Funcionários:");
                    empresa.exibirFuncionarios();
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
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
