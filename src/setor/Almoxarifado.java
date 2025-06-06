package setor;

import enumeracao.Categoria;
import exception.ProdutoNaoEncontradoException;
import exception.QuantidadeLimiteFuncionariosException;
import model.Produto;

import java.util.ArrayList;
import java.util.Random;

public class Almoxarifado extends Setor {

    static Random gerador = new Random();
    private int contador = 1;
    private static ArrayList<Produto> produtos;
    private static ArrayList<Produto> produtosIniciais;

    public Almoxarifado(String nome) throws QuantidadeLimiteFuncionariosException {
        super(nome);
        setQtdLimite(3);

        if(contador > getQtdLimite()){
            throw new QuantidadeLimiteFuncionariosException("Quantidade excedeu limite de funcionarios");
        }
        contador++;
    }

    // Cria 10 produtos iniciais com nome de um array
    public void criarProdutosIniciais() {
        produtos = new ArrayList<Produto>();
        String[] nomes = {"Pasta de dente", "Xarope para tosse", "Gel condicionador de cabelo", "Shampoo clear", "Allegra", "Dipirona", "Barra de proteína", "Coca-cola 600ML", "Ibuprofeno", "Ledx"};
        for(int i = 0; i < 10; i++) {
            double valorCompra = gerador.nextDouble(3, 20);
            int quantidadeEstoque = gerador.nextInt(5, 300);
            produtos.add(new Produto(nomes[i], valorCompra, valorCompra + (valorCompra * 0.15), quantidadeEstoque, Categoria.HIGIENE));
        }
    }

    // Percorre a lista de produtos e remove o que for igual ao parâmetro
    public String removerProduto(Produto produto) throws ProdutoNaoEncontradoException {
        for(Produto p : produtos) {
            if(p.equals(produto)) {
                produtos.remove(p);
                return "Produto " + "*** produto.getNome() ***" + "removido com sucesso!";
            }
        }
        throw new ProdutoNaoEncontradoException("Produto " + " *** produto.getNome() ***" + " não encontrado.");
    }

    // Percorre a lista de produtos exibindo cada produto
    public void exibirProdutos() {
        for(Produto p : produtos) {
            System.out.println(p.exibirInformacoes());
        }
    }

    // Retorna todos os produtos
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public void verificarProduto(Produto produto) throws ProdutoNaoEncontradoException{
        for(Produto p : produtos) {
            if(p.equals(produto)) {
                return;
            }
        }
        throw new ProdutoNaoEncontradoException("Produto não encontrado no estoque.");
    }
}
