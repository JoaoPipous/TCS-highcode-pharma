package model;

import enumeracao.Status;

import java.time.Month;
import java.time.Year;
import java.util.Random;
import java.util.ArrayList;

public class Caixa {
    static Random gerador = new Random();
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

    // Inicia 2 variáveis, saída (compra de produtos) e entrada (venda de produtos)
    // Percorre a lista de saídas, se a saída atual estiver programada e
    // for do mesmo mês passado por parâmetro, soma a saida o mesmo é feito para as entradas
    // No final, retorna a diferença entre entradas e saídas = (lucro mensal)
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

    // Inicia 2 variáveis, saída (compra de produtos) e entrada (venda de produtos)
    // Percorre a lista de saídas, se a saída atual estiver programada e
    // for do mesmo ano passado por parâmetro, soma a saida o mesmo é feito para as entradas
    // No final, retorna a diferença entre entradas e saídas = (lucro anual)
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
}