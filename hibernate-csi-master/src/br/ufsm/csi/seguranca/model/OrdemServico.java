package br.ufsm.csi.seguranca.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "OrdemServico")
public class OrdemServico {

    private Date dataEntrada;
    private String descricaoReparos;
    private Long codigo;
    private Fase faseAtual;
    private Orcamento orcamento;

    @Column(name = "dataEntrada")
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    @Column(name = "descricaoReparos", length = 5000)
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
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
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

}
