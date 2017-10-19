package br.ufsm.csi.seguranca.model;

import java.util.Date;

public class Orcamento {

    private String descricaoReparos;
    private Date entrada;
    private double valorTotal;
    private double valorTotalMO;
    private double valorTotalPecas;
    private int codigo;
    private Veiculo veiculo;

    public boolean gerarOS(){
        return false;
    }

    //Gerados:
    public String getDescricaoReparos() {
        return descricaoReparos;
    }

    public void setDescricaoReparos(String descricaoReparos) {
        this.descricaoReparos = descricaoReparos;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getValorTotalMO() {
        return valorTotalMO;
    }

    public void setValorTotalMO(double valorTotalMO) {
        this.valorTotalMO = valorTotalMO;
    }

    public double getValorTotalPecas() {
        return valorTotalPecas;
    }

    public void setValorTotalPecas(double valorTotalPecas) {
        this.valorTotalPecas = valorTotalPecas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
