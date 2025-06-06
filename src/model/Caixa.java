package model;

import enumeracao.Categoria;
import exception.ProdutoNaoEncontradoException;

import java.time.Month;
import java.time.Year;
import java.util.Random;
import java.util.ArrayList;

public class Caixa {
    private double valorTotal;
    private ArrayList<Negocio> entradas;
    private ArrayList<Negocio> saidas;

    public Caixa(double valorTotal) {
        this.valorTotal = valorTotal;
        this.entradas = new ArrayList<Negocio>();
        this.saidas = new ArrayList<Negocio>();
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

    public void registrarCompra(Negocio compra) {
        saidas.add(compra);
        valorTotal -= compra.getValorNegocio();
    }

    public void registrarVenda(Negocio venda) {
        saidas.add(venda);
        valorTotal += venda.getValorNegocio();
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

    // Inicia 2 variáveis, saída (compra de produtos) e entrada (venda de produtos)
    // Percorre a lista de saídas, se a saída atual estiver programada e
    // for do mesmo mês passado por parâmetro, soma a saida o mesmo é feito para as entradas
    // No final, retorna a diferença entre entradas e saídas = (lucro mensal)
    public double estimarLucroMensal(Month mes) {
        double saida = 0, entrada = 0;

        for(Negocio c : saidas) {
            // Verificar se a entrega está com status aberto
            Month mesProgramado = c.getDataProgramada().getMonth();
            if(mesProgramado.equals(mes)) {
                saida += c.getValorNegocio();
            }
        }

        for(Negocio v : entradas) {
            // Verificar se a entrega está com status aberto
            Month mesProgramado = v.getDataProgramada().getMonth();
            if(mesProgramado.equals(mes)) {
                entrada += v.getValorNegocio();
            }
        }

        return entrada - saida;
    }

    // Inicia 2 variáveis, saída (compra de produtos) e entrada (venda de produtos)
    // Percorre a lista de saídas, se a saída atual estiver programada e
    // for do mesmo ano passado por parâmetro, soma a saida o mesmo é feito para as entradas
    // No final, retorna a diferença entre entradas e saídas = (lucro anual)
    public double estimarLucroAnual(Year ano) {
        double saida = 0, entrada = 0;

        for(Negocio c : saidas) {
            // Verificar se a entrega está com status aberto
            Year anoProgramado = Year.of(c.getDataProgramada().getYear());
            if(anoProgramado.equals(ano)) {
                saida += c.getValorNegocio();
            }
        }

        for(Negocio v : entradas) {
            // Verificar se a entrega está com status aberto
            Year anoProgramado = Year.of(v.getDataProgramada().getYear());
            if(anoProgramado.equals(ano)) {
                entrada += v.getValorNegocio();
            }
        }

        return entrada - saida;
    }
}