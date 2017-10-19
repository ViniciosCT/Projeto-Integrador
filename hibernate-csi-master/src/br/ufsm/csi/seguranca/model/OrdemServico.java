package br.ufsm.csi.seguranca.model;

import java.util.Date;

public class OrdemServico {

    private Date dataEntrada;
    private String descricaoReparos;
    private int codigo;
    private Fase faseAtual;
    private Orcamento orcamento;

    public Date getTempoPercorrido(Fase fase){
        return null;
    }

    public boolean setFase(Fase fase){
        return false;
    }

    //Gerados:
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDescricaoReparos() {
        return descricaoReparos;
    }

    public void setDescricaoReparos(String descricaoReparos) {
        this.descricaoReparos = descricaoReparos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Fase getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(Fase faseAtual) {
        this.faseAtual = faseAtual;
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
}
