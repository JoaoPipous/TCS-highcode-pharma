package model;

import enumeracao.Status;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
// --- CORREÇÃO: Importação que faltava foi adicionada aqui ---
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Caixa {
    static Random gerador = new Random();
    private double valorTotal;
    private ArrayList<Negocio> entradas; // Vendas
    private ArrayList<Negocio> saidas;   // Compras

    public Caixa(double valorTotal) {
        this.valorTotal = valorTotal;
        this.entradas = new ArrayList<Negocio>();
        this.saidas = new ArrayList<Negocio>();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void addValor(double valor) {
        this.valorTotal += valor;
    }

    public void removerValor(double valor) {
        this.valorTotal -= valor;
    }

    public List<Negocio> getNegocios() {
        ArrayList<Negocio> todosOsNegocios = new ArrayList<>();
        todosOsNegocios.addAll(entradas);
        todosOsNegocios.addAll(saidas);
        return todosOsNegocios;
    }

    public ArrayList<Negocio> getEntradas() {
        return entradas;
    }

    public ArrayList<Negocio> getSaidas() {
        return saidas;
    }

    public void registrarCompra(Negocio compra) {
        saidas.add(compra);
    }

    public void registrarVenda(Negocio venda) {
        entradas.add(venda);
    }

    public ArrayList<String> exibirVendas() {
        ArrayList<String> vendasString = new ArrayList<>();
        if (entradas.isEmpty()) {
            vendasString.add("A empresa não possui vendas registradas.");
        } else {
            for (Negocio v : entradas) {
                vendasString.add(v.exibirDados());
            }
        }
        return vendasString;
    }

    public ArrayList<String> exibirCompras() {
        ArrayList<String> comprasString = new ArrayList<>();
        if (saidas.isEmpty()) {
            comprasString.add("A empresa não possui compras registradas.");
        } else {
            for (Negocio c : saidas) {
                comprasString.add(c.exibirDados());
            }
        }
        return comprasString;
    }

    public String exibirNegociosAbertos() {
        StringBuilder sb = new StringBuilder();
        for (Negocio n : getNegocios()) {
            if (n.getStatus() == Status.ABERTO) {
                sb.append(n.exibirDados()).append("\n");
            }
        }
        return sb.toString();
    }

    public double estimarLucroMensal(int mes) {
        double saida = 0, entrada = 0;
        Month mesEscolhido = Month.of(mes);

        for (Negocio c : saidas) {
            if (c.getStatus() == Status.ABERTO && c.getDataProgramada() != null) {
                if (c.getDataProgramada().getMonth() == mesEscolhido) {
                    saida += c.calcularValorTotal();
                }
            }
        }

        for (Negocio v : entradas) {
            if (v.getStatus() == Status.ABERTO && v.getDataProgramada() != null) {
                if (v.getDataProgramada().getMonth() == mesEscolhido) {
                    entrada += v.calcularValorTotal();
                }
            }
        }
        return entrada - saida;
    }

    public double getLucroMensal() {
        Month mesAtual = LocalDate.now().getMonth();
        double entrada = 0, saida = 0;

        for (Negocio c : saidas) {
            if (c.getStatus() == Status.FINALIZADO) {
                LocalDateTime dataFinal = c.getDataFinalizacao() != null ? c.getDataFinalizacao() : c.getDataNegocio();
                if (dataFinal.getMonth() == mesAtual) {
                    saida += c.calcularValorTotal();
                }
            }
        }

        for (Negocio v : entradas) {
            if (v.getStatus() == Status.FINALIZADO) {
                LocalDateTime dataFinal = v.getDataFinalizacao() != null ? v.getDataFinalizacao() : v.getDataNegocio();
                if (dataFinal.getMonth() == mesAtual) {
                    entrada += v.calcularValorTotal();
                }
            }
        }
        return entrada - saida;
    }

    public double estimarLucroAnual(int ano) {
        double saida = 0, entrada = 0;

        for (Negocio c : saidas) {
            if (c.getStatus() == Status.ABERTO && c.getDataProgramada() != null) {
                if (c.getDataProgramada().getYear() == ano) {
                    saida += c.calcularValorTotal();
                }
            }
        }

        for (Negocio v : entradas) {
            if (v.getStatus().equals(Status.ABERTO) && v.getDataProgramada() != null) {
                if (v.getDataProgramada().getYear() == ano) {
                    entrada += v.calcularValorTotal();
                }
            }
        }
        return entrada - saida;
    }
}