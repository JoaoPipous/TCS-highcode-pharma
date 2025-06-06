package model;

import enumeracao.Categoria;
import enumeracao.Status;
import exception.ProdutoNaoEncontradoException;

import java.time.Month;
import java.time.Year;
import java.util.Random;
import java.util.ArrayList;

public class Caixa {
    static Random gerador = new Random();
    private double valorTotal;
    private ArrayList<Negocio> entradas;
    private ArrayList<Negocio> saidas;
    private ArrayList<Produto> produtos;
    private ArrayList<Produto> produtosIniciais;

    public Caixa(double valorTotal) {
        this.valorTotal = valorTotal;
        this.entradas = new ArrayList<Negocio>();
        this.saidas = new ArrayList<Negocio>();
        this.produtosIniciais = new ArrayList<Produto>();
        criarProdutosIniciais();
        this.produtos = produtosIniciais;
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

        for(ItemNegocio item : compra.getProduto()){
            item.getProduto().setQtdEstoque(item.getProduto().getQtdEstoque() + 1);
        }
    }

    public void registrarVenda(Negocio venda) {
        saidas.add(venda);

        for(ItemNegocio item : venda.getProduto()){
            item.getProduto().setQtdEstoque(item.getProduto().getQtdEstoque() - 1);
        }
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

    public String exibirNegociosAbertos() {

        StringBuilder sb = new StringBuilder();

        for(Negocio e : entradas) {
            if(e.getStatus() == Status.ABERTO) {
                sb.append(e.exibirDados() + "\n");
            }
        }

        for(Negocio s : saidas) {
            if(s.getStatus() == Status.ABERTO) {
                sb.append(s.exibirDados() + "\n");
            }
        }

        return sb.toString();
    }

    public double estimarLucroMensal(int mes) {
        double saida = 0, entrada = 0;

        for(Negocio c : saidas) {
            // Verificar se a entrega está com status aberto
            Month mesEscolhido = Month.of(mes);
            Month mesProgramado = c.getDataProgramada().getMonth();
            if(mesProgramado.equals(mesEscolhido)) {
                saida += c.getValorNegocio();
            }
        }

        for(Negocio v : entradas) {
            // Verificar se a entrega está com status aberto
            Month mesEscolhido = Month.of(mes);
            Month mesProgramado = v.getDataProgramada().getMonth();
            if(mesProgramado.equals(mesEscolhido)) {
                entrada += v.getValorNegocio();
            }
        }

        return entrada - saida;
    }

    public double estimarLucroAnual(int ano) {
        double saida = 0, entrada = 0;

        for(Negocio c : saidas) {
            // Verificar se a entrega está com status aberto
            Year anoEscolhido = Year.of(ano);
            Year anoProgramado = Year.of(c.getDataProgramada().getYear());
            if(anoProgramado.equals(anoEscolhido)) {
                saida += c.getValorNegocio();
            }
        }

        for(Negocio v : entradas) {
            // Verificar se a entrega está com status aberto
            Year anoEscolhido = Year.of(ano);
            Year anoProgramado = Year.of(v.getDataProgramada().getYear());
            if(anoProgramado.equals(anoEscolhido)) {
                entrada += v.getValorNegocio();
            }
        }

        return entrada - saida;
    }

    public void exibirProdutos() {
        for(Produto p : produtos) {
            System.out.println(p.exibirInformacoes());
        }
    }
}