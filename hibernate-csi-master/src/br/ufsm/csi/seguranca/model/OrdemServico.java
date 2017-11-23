package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "OrdemServico")
public class OrdemServico {

    private Date dataEntrada;
    private String descricaoReparos;
    private int codigo;
    private Fase faseAtual;
    //private ArrayList<Fase> fases;
    private Orcamento orcamento;


    public Date getTempoPercorrido(Fase fase){
        return null;
    }

    public boolean setFase(Fase fase){
        return false;
    }

    //Gerados:
    @Column(name = "dataEntrada")
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @Column(name = "descricaoReparos")
    public String getDescricaoReparos() {
        return descricaoReparos;
    }

    public void setDescricaoReparos(String descricaoReparos) {
        this.descricaoReparos = descricaoReparos;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
    @Column(name="codOrdemServico")
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "codFase")
    public Fase getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(Fase faseAtual) {
        this.faseAtual = faseAtual;
    }

    @ManyToOne
    @JoinColumn(name = "codOrcamento")
    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }

//    @ManyToMany(mappedBy="ordens")
//    public ArrayList<Fase> getFases() {
//        return fases;
//    }
//
//    public void setFases(ArrayList<Fase> fases) {
//        this.fases = fases;
//    }
}
