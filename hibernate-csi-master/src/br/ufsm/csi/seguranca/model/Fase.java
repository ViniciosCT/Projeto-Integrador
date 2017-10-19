package br.ufsm.csi.seguranca.model;

import java.util.ArrayList;

public class Fase {

    private String nome;
    private int codigo;
    private ArrayList<OrdemServico> Ordens;

    public double getFaturamento(){
        return 0;
    }

    public double getQuantidadeVeiculos(){
        return 0;
    }

    //Gerados:
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<OrdemServico> getOrdens() {
        return Ordens;
    }

    public void setOrdens(ArrayList<OrdemServico> ordens) {
        Ordens = ordens;
    }
}
