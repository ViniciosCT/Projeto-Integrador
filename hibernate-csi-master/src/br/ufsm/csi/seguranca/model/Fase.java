package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name="Fase")
public class Fase {

    private String nome;
    private int codigo;
    private ArrayList<OrdemServico> ordens;

    public double getFaturamento(){
        return 0;
    }

    public double getQuantidadeVeiculos(){
        return 0;
    }

    //Gerados:
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
    @Column(name="id")
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @ManyToMany(mappedBy = "fases")
    public ArrayList<OrdemServico> getOrdens() {
        return ordens;
    }

    public void setOrdens(ArrayList<OrdemServico> ordens) {
        this.ordens = ordens;
    }
}