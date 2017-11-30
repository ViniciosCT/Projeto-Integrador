package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="OSXFase")
public class OSXFase {

    private Long codigo;
    private OrdemServico os;
    private Fase fase;
    private Date tempoInicial;
    private Date tempoFinal;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
    @Column(name="codOSXFase")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "codOrdemServico")
    public OrdemServico getOs() {
        return os;
    }

    public void setOs(OrdemServico os) {
        this.os = os;
    }

    @ManyToOne
    @JoinColumn(name = "codFase")
    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    @Column(name="tempoInicial")
    public Date getTempoInicial() {
        return tempoInicial;
    }

    public void setTempoInicial(Date tempoInicial) {
        this.tempoInicial = tempoInicial;
    }

    @Column(name="tempoFinal")
    public Date getTempoFinal() {
        return tempoFinal;
    }

    public void setTempoFinal(Date tempoFinal) {
        this.tempoFinal = tempoFinal;
    }
}
