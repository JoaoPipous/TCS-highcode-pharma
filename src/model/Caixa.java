package model;

import exception.ProdutoNaoEncontradoException;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

public class Caixa {
    private double valorTotal;
    private ArrayList<Negocio> entradas;
    private ArrayList<Negocio> saidas;
    private ArrayList<Produto> produtos;

    public Caixa(double valorTotal) {
        this.valorTotal = valorTotal;
        this.entradas = new ArrayList<Negocio>();
        this.saidas = new ArrayList<Negocio>();
        this.produtos = criarProdutosIniciais();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public ArrayList<Negocio> getEntradas() {
        return entradas;
    }

    public ArrayList<Negocio> getSaidas() {
        return saidas;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    //    USAR O FOR COM CONTAGEM ATÉ 10 PARA ADICIONAR 10 PRODUTOS A LISTA
    //    PRODUTOS INSERIDOS MANUALMENTE OU UTILIZANDO O RANDOM PARA GERAR VALORES

    public ArrayList<Produto> criarProdutosIniciais() {
        ArrayList<Produto> produtosIniciais = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            // *** produtosIniciais.add() ***
        }
        return produtosIniciais;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public String removerProduto(Produto produto) throws ProdutoNaoEncontradoException {
        for(Produto p : produtos) {
            if(p.equals(produto)) {
                produtos.remove(p);
                return "Produto " + "*** produto.getNome() ***" + "removido com sucesso!";
            }
        }
        throw new ProdutoNaoEncontradoException("Produto " + " *** produto.getNome() ***" + " não encontrado.");
    }

    public void registrarCompra(Negocio compra) {
        saidas.add(compra);
        // *** AUMENTAR ESTOQUE ***
    }

    public void registrarVenda(Negocio venda) {
        saidas.add(venda);
        // *** DIMINUIR ESTOQUE ***
    }

    public ArrayList<String> exibirVendas() {
        ArrayList<String> vendasString = new ArrayList<String>();
        if(entradas.isEmpty()) {

        } else {
            for(Negocio v : entradas) {
                vendasString.add("Negócio feito em: " + v.getDataNegocioFormatada());
            }
        }
        return vendasString;
    }

    public ArrayList<String> exibirCompras() {
        ArrayList<String> comprasString = new ArrayList<String>();
        if(saidas.isEmpty()) {

        } else {
            for(Negocio c : saidas) {
                comprasString.add("Negócio feito em: " + c.getDataNegocioFormatada());
            }
        }
        return comprasString;
    }

    public double estimarLucroMensal(Month mes) {
        double saida = 0, entrada = 0;

        for(Negocio c : saidas) {
            Month mesProgramado = c.getDataProgramada().getMonth();
            if(mesProgramado.equals(mes)) {
                saida += c.getValorNegocio();
            }
        }

        for(Negocio v : entradas) {
            Month mesProgramado = v.getDataProgramada().getMonth();
            if(mesProgramado.equals(mes)) {
                entrada += v.getValorNegocio();
            }
        }

        return entrada - saida;
    }

    public double estimarLucroAnual(Year ano) {
        double saida = 0, entrada = 0;

        for(Negocio c : saidas) {
            Year anoProgramado = Year.of(c.getDataProgramada().getYear());
            if(anoProgramado.equals(ano)) {
                saida += c.getValorNegocio();
            }
        }

        for(Negocio v : entradas) {
            Year anoProgramado = Year.of(v.getDataProgramada().getYear());
            if(anoProgramado.equals(ano)) {
                entrada += v.getValorNegocio();
            }
        }

        return entrada - saida;
    }
}