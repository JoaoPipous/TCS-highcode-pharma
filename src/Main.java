import enumeracao.Status;
import enumeracao.TipoNegocio;
import enumeracao.Transportadora;
import exception.*;
import model.Funcionario;
import model.ItemNegocio;
import model.Negocio;
import model.Produto;
import setor.Almoxarifado;
import setor.Setor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // --- ADIÇÃO: Método auxiliar para ler números inteiros de forma segura ---
    private static int lerInteiro(Scanner scanner) {
        while (true) {
            try {
                // Tenta ler a linha inteira e convertê-la para um número
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Se falhar, avisa o usuário e o loop continua, pedindo a entrada novamente.
                System.out.println("Erro: Entrada inválida. Por favor, digite um número inteiro.");
            }
        }
    }

    // --- ADIÇÃO: Método auxiliar para ler números com casas decimais de forma segura ---
    private static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número (ex: 10.50).");
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Empresa empresa = new Empresa();

        // Código inicial sem alterações
        for (Setor s : empresa.getSetores()) {
            if (s instanceof Almoxarifado) {
                ((Almoxarifado) s).exibirProdutos();
                break;
            }
        }

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar funcionário");
            System.out.println("2 - Adicionar produto");
            System.out.println("3 - Realizar uma compra");
            System.out.println("4 - Realizar uma venda");
            System.out.println("5 - Listar funcionários");
            System.out.println("6 - Listar produtos");
            System.out.println("7 - Listar negócios da empresa (vendas e compras)");
            System.out.println("8 - Exibir transportadoras");
            System.out.println("9 - Mostrar o valor total do caixa da empresa e estimativa de lucros");
            System.out.println("10 - Exibir negócios em aberto");
            System.out.println("11 - Exibir setores da empresa");
            System.out.println("13 - Finalizar negócio em aberto");
            System.out.println("14 - Sair");

            // --- CORREÇÃO: Usando o novo método seguro para ler a opção ---
            int opcao = lerInteiro(sc);

            switch (opcao) {
                case 1: { // Adicionar Funcionário
                    try {
                        System.out.println("Digite o nome do funcionário:");
                        String nomeFuncionario = sc.nextLine();

                        System.out.println("Digite o sobrenome do funcionário:");
                        String sobrenomeFuncionario = sc.nextLine();

                        System.out.println("Digite o código único do funcionário:");
                        String codigoFuncionario = sc.nextLine();

                        System.out.println("Digite a idade do funcionário:");
                        int idadeFuncionario = lerInteiro(sc); // Usando método seguro

                        System.out.println(empresa.exibirGeneros());
                        System.out.println("Digite o número do gênero correspondente do funcionário:");
                        int numGenero = lerInteiro(sc); // Usando método seguro

                        System.out.println(empresa.exibirSetores());
                        System.out.println("Digite o número do setor correspondente do funcionário:");
                        int numSetor = lerInteiro(sc); // Usando método seguro

                        Setor setorDefinido = empresa.definirSetor(numSetor);
                        Funcionario funcionario = new Funcionario(nomeFuncionario, sobrenomeFuncionario, codigoFuncionario, idadeFuncionario, numGenero, setorDefinido);

                        empresa.addFuncionario(funcionario);
                        System.out.println("Funcionário adicionado com sucesso!");

                    } catch (CodigoUnicoExistenteException | GeneroInvalidoException | SetorInvalidoException | QuantidadeLimiteFuncionariosException e) {
                        System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
                    }
                    break;
                }

                case 2: { // Adicionar Produto
                    try {
                        System.out.println("Digite o nome do produto:");
                        String nomeProduto = sc.nextLine();

                        System.out.println("Digite o valor de compra do produto:");
                        double valorCompra = lerDouble(sc); // Usando método seguro

                        System.out.println("Digite o valor de venda do produto:");
                        double valorVenda = lerDouble(sc); // Usando método seguro

                        System.out.println("Digite a quantidade em estoque atual do produto:");
                        int qtdEstoque = lerInteiro(sc); // Usando método seguro

                        System.out.println("Escolha a categoria do produto:");
                        System.out.println("1- Medicamento  2- Higiene  3- Cosmético  4- Alimentício");
                        int categoria = lerInteiro(sc); // Usando método seguro

                        Produto produto = new Produto(nomeProduto, valorCompra, valorVenda, qtdEstoque, categoria);

                        for (Setor s : empresa.getSetores()) {
                            if (s instanceof Almoxarifado) {
                                ((Almoxarifado) s).adicionarProduto(produto);
                                System.out.println("Produto adicionado com sucesso!");
                                break;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro ao criar produto: " + e.getMessage());
                    }
                    break;
                }

                case 3: { // Realizar uma Compra
                    ArrayList<ItemNegocio> produtosCompra = new ArrayList<>();
                    ArrayList<Funcionario> funcionariosEnvolvidosCompra = new ArrayList<>();

                    System.out.println("\nQual o status da compra?");
                    System.out.println("1 - Em aberto");
                    System.out.println("2 - Finalizada");

                    int statusOpcao = lerInteiro(sc); // Usando método seguro
                    Status status = (statusOpcao == 1 ? Status.ABERTO : Status.FINALIZADO);
                    LocalDateTime dataHoraLida = null;

                    System.out.println("\nQual a transportadora?");
                    System.out.println("1 - Londrina");
                    System.out.println("2 - Cambé");
                    System.out.println("3 - Rolândia");

                    int transportadora = lerInteiro(sc);
                    Transportadora t = null;
                    try {
                        switch (transportadora) {
                            case 1: t = Transportadora.LONDRINA; break;
                            case 2: t = Transportadora.CAMBE; break;
                            case 3: t = Transportadora.ROLANDIA; break;
                            default: throw new IllegalArgumentException("Entrada inválida. Transportadora não definida.");
                        }
                    } catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    if (status.equals(Status.ABERTO)) {
                        while (true) {
                            System.out.println("\nInsira a data de finalização da compra (formato: dd/MM/yyyy HH:mm:ss):");
                            try {
                                String data = sc.nextLine();
                                dataHoraLida = LocalDateTime.parse(data, formatador);
                                if (dataHoraLida.isBefore(LocalDateTime.now())) {
                                    throw new DataValidaException("A data de finalização deve ser após a data atual.");
                                }
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Erro: O formato digitado está incorreto.");
                            } catch (DataValidaException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    while (true) {
                        System.out.println("\nEscolha o(s) produto da compra ('0' para finalizar seleção): \n");
                        int contador = 1;
                        for (Produto p : Almoxarifado.getProdutos()) {
                            System.out.println(contador + " - " + p.exibirInformacoes());
                            contador++;
                        }
                        System.out.println("0 - Finalizar seleção de produtos");

                        int produtoEscolha = lerInteiro(sc); // Usando método seguro

                        if (produtoEscolha == 0) {
                            if (produtosCompra.isEmpty()) {
                                System.out.println("Nenhum produto foi adicionado. A criação da compra foi cancelada.");
                            }
                            break;
                        }

                        System.out.println("\nInforme a quantidade: ");
                        int quantidadeProduto = lerInteiro(sc); // Usando método seguro

                        try {
                            if (produtoEscolha >= 1 && produtoEscolha <= Almoxarifado.getProdutos().size()) {
                                produtosCompra.add(new ItemNegocio(Almoxarifado.getProdutos().get(produtoEscolha - 1), quantidadeProduto));
                                System.out.println("Produto adicionado à compra.");
                            } else {
                                throw new ProdutoNaoEncontradoException("Produto não encontrado.");
                            }
                        } catch (ProdutoNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (produtosCompra.isEmpty()) {
                        break;
                    }

                    while (true) {
                        System.out.println("\nQuais funcionários participaram do negócio? ('0' para sair)\n");
                        for (int i = 0; i < empresa.getFuncionarios().size(); i++) {
                            Funcionario f = empresa.getFuncionarios().get(i);
                            System.out.printf("%d - %s %s (Cód: %s)\n", (i + 1), f.getNome(), f.getSobrenome(), f.getCodigoFuncionario());
                        }
                        System.out.println("0 - Sair");

                        int funcionarioEscolha = lerInteiro(sc); // Usando método seguro
                        if (funcionarioEscolha == 0) break;

                        if (funcionarioEscolha > 0 && funcionarioEscolha <= empresa.getFuncionarios().size()) {
                            Funcionario escolhido = empresa.getFuncionarios().get(funcionarioEscolha - 1);
                            if (!funcionariosEnvolvidosCompra.contains(escolhido)) {
                                funcionariosEnvolvidosCompra.add(escolhido);
                                System.out.println("Funcionário adicionado.");
                            } else {
                                System.out.println("Esse funcionário já foi adicionado.");
                            }
                        } else {
                            System.out.println("Opção de funcionário inválida.");
                        }
                    }

                    Negocio negocio;
                    if (status.equals(Status.FINALIZADO)) {
                        negocio = new Negocio(status, funcionariosEnvolvidosCompra, produtosCompra, TipoNegocio.COMPRA, t);
                    } else {
                        negocio = new Negocio(status, produtosCompra, funcionariosEnvolvidosCompra, dataHoraLida, TipoNegocio.COMPRA, t);
                    }

                    empresa.registrarCompra(negocio);
                    System.out.println("Compra registrada com sucesso!");
                    break;
                }

                case 4: { // Realizar uma Venda
                    ArrayList<ItemNegocio> produtosVenda = new ArrayList<>();
                    ArrayList<Funcionario> funcionariosEnvolvidosVenda = new ArrayList<>();

                    System.out.println("\nQual o status da venda?");
                    System.out.println("1 - Em aberto");
                    System.out.println("2 - Finalizada");

                    int statusOpcao = lerInteiro(sc); // Usando método seguro
                    Status status = (statusOpcao == 1 ? Status.ABERTO : Status.FINALIZADO);
                    LocalDateTime dataHoraLida = null;

                    System.out.println("\nQual a transportadora?");
                    System.out.println("1 - Londrina");
                    System.out.println("2 - Cambé");
                    System.out.println("3 - Rolândia");

                    int transportadora = lerInteiro(sc);
                    Transportadora t = null;
                    try {
                        switch (transportadora) {
                            case 1: t = Transportadora.LONDRINA; break;
                            case 2: t = Transportadora.CAMBE; break;
                            case 3: t = Transportadora.ROLANDIA; break;
                            default: throw new IllegalArgumentException("Entrada inválida. Transportadora não definida.");
                        }
                    } catch(IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    if (status.equals(Status.ABERTO)) {
                        while (true) {
                            System.out.println("\nInsira a data de finalização da venda (formato: dd/MM/yyyy HH:mm:ss):");
                            try {
                                String data = sc.nextLine();
                                dataHoraLida = LocalDateTime.parse(data, formatador);
                                if (dataHoraLida.isBefore(LocalDateTime.now())) {
                                    throw new DataValidaException("A data de finalização deve ser após a data atual.");
                                }
                                break;
                            } catch (DateTimeParseException e) {
                                System.out.println("Erro: O formato digitado está incorreto.");
                            } catch (DataValidaException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    while (true) {
                        System.out.println("\nEscolha o produto da venda ('0' para finalizar seleção): \n");
                        int contador = 1;
                        for (Produto p : Almoxarifado.getProdutos()) {
                            System.out.println(contador + " - " + p.exibirInformacoes());
                            contador++;
                        }
                        System.out.println("0 - Finalizar seleção de produtos");

                        int produtoEscolha = lerInteiro(sc); // Usando método seguro

                        if (produtoEscolha == 0) {
                            if (produtosVenda.isEmpty()) {
                                System.out.println("Nenhum produto foi adicionado. A criação da venda foi cancelada.");
                            }
                            break;
                        }

                        System.out.println("\nInforme a quantidade: ");
                        int quantidadeProduto = lerInteiro(sc); // Usando método seguro

                        try {
                            if (produtoEscolha >= 1 && produtoEscolha <= Almoxarifado.getProdutos().size()) {
                                produtosVenda.add(new ItemNegocio(Almoxarifado.getProdutos().get(produtoEscolha - 1), quantidadeProduto));
                                System.out.println("Produto adicionado à venda.");
                            } else {
                                throw new ProdutoNaoEncontradoException("Produto não encontrado.");
                            }
                        } catch (ProdutoNaoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    if (produtosVenda.isEmpty()) {
                        break;
                    }

                    while (true) {
                        System.out.println("\nQuais funcionários participaram do negócio? ('0' para sair)\n");
                        for (int i = 0; i < empresa.getFuncionarios().size(); i++) {
                            Funcionario f = empresa.getFuncionarios().get(i);
                            System.out.printf("%d - %s %s (Cód: %s)\n", (i + 1), f.getNome(), f.getSobrenome(), f.getCodigoFuncionario());
                        }
                        System.out.println("0 - Sair");

                        int funcionarioEscolha = lerInteiro(sc); // Usando método seguro
                        if (funcionarioEscolha == 0) break;

                        if (funcionarioEscolha > 0 && funcionarioEscolha <= empresa.getFuncionarios().size()) {
                            Funcionario escolhido = empresa.getFuncionarios().get(funcionarioEscolha - 1);
                            if (!funcionariosEnvolvidosVenda.contains(escolhido)) {
                                funcionariosEnvolvidosVenda.add(escolhido);
                                System.out.println("Funcionário adicionado.");
                            } else {
                                System.out.println("Esse funcionário já foi adicionado.");
                            }
                        } else {
                            System.out.println("Opção de funcionário inválida.");
                        }
                    }

                    Negocio negocio;
                    if (status.equals(Status.FINALIZADO)) {
                        negocio = new Negocio(status, funcionariosEnvolvidosVenda, produtosVenda, TipoNegocio.VENDA, t);
                    } else {
                        negocio = new Negocio(status, produtosVenda, funcionariosEnvolvidosVenda, dataHoraLida, TipoNegocio.VENDA, t);
                    }

                    try {
                        empresa.registrarVenda(negocio);
                        System.out.println("Venda registrada com sucesso!");
                    } catch (EstoqueInsuficienteException e) {
                        System.out.println("Erro ao registrar venda: " + e.getMessage());
                    }
                    break;
                }

                case 5:
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    System.out.println("Lista de Funcionários:");
                    empresa.exibirFuncionarios();
                    System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
                    break;

                case 6:
                    System.out.println("Lista de produtos:\n");
                    for (Setor s : empresa.getSetores()) {
                        if (s instanceof Almoxarifado) {
                            ((Almoxarifado) s).exibirProdutos();
                            break;
                        }
                    }
                    break;

                case 7:
                    System.out.println("Lista de negócios:\n");
                    System.out.println(empresa.getCaixa().exibirCompras());
                    System.out.println(empresa.getCaixa().exibirVendas());
                    break;

                case 8:
                    System.out.println("Transportadoras:\n");
                    for(Transportadora t : Transportadora.values()) {
                        System.out.print(t.getCidadeTransportadora() + " ");
                    }
                    break;

                case 9:
                    System.out.println("Para verificar a estimativa mensal, digite o número referente ao mês desejado: (1 a 12)");
                    int mensal = lerInteiro(sc); // Usando método seguro

                    System.out.println("Para verificar a estimativa anual, digite o ano desejado:");
                    int anual = lerInteiro(sc); // Usando método seguro

                    System.out.printf("Valor total do caixa: R$%.2f\nEstimativa mensal: R$%.2f\nEstimativa anual: R$%.2f\n", empresa.getCaixa().getValorTotal(), empresa.getCaixa().estimarLucroMensal(mensal), empresa.getCaixa().estimarLucroAnual(anual));
                    break;

                case 10:
                    System.out.println("Negócios em aberto:\n");
                    System.out.println(empresa.getCaixa().exibirNegociosAbertos());
                    break;

                case 11:
                    System.out.println("\nExibindo setores:");
                    for (Setor setor : empresa.getSetores()) {
                        System.out.println(setor.exibirSetor());
                    }
                    break;

                case 12:
                    System.out.println("Opção 12 não existe. A opção de sair é 14.");
                    break;

                case 13: {
                    List<Negocio> negociosAbertos = empresa.getCaixa().getNegocios().stream()
                            .filter(n -> n.getStatus() == Status.ABERTO)
                            .collect(Collectors.toList());

                    if (negociosAbertos.isEmpty()) {
                        System.out.println("Não há negócios em aberto para finalizar.");
                        break;
                    }

                    System.out.println("\nQual negócio você deseja finalizar?");
                    for (int i = 0; i < negociosAbertos.size(); i++) {
                        System.out.printf("%d - %s\n", (i + 1), negociosAbertos.get(i).exibirResumo());
                    }
                    System.out.println("0 - Cancelar");

                    int escolha = lerInteiro(sc); // Usando método seguro

                    if (escolha == 0) {
                        System.out.println("Operação cancelada.");
                        break;
                    }

                    try {
                        if (escolha > 0 && escolha <= negociosAbertos.size()) {
                            Negocio negocioParaFinalizar = negociosAbertos.get(escolha - 1);
                            empresa.finalizarNegocioAberto(negocioParaFinalizar);
                            System.out.println("Negócio finalizado com sucesso!");
                        } else {
                            System.out.println("Opção inválida.");
                        }
                    } catch (EstoqueInsuficienteException e) {
                        System.out.println("ERRO AO FINALIZAR VENDA: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                    }
                    break;
                }

                case 14:
                    System.out.println("Saindo do sistema...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
