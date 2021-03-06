package br.ufsm.csi.seguranca.model;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Orcamento")
public class Orcamento {

    private String descricaoReparos;
    private Date entrada;
    private double valorTotal;
    private double valorTotalMO;
    private double valorTotalPecas;
    private Long codigo;
    private Veiculo veiculo;


    @Column(name = "descricaoReparos", length = 5000)
    public String getDescricaoReparos() {
        return descricaoReparos;
    }

    public void setDescricaoReparos(String descricaoReparos) {
        this.descricaoReparos = descricaoReparos;
    }

    @Column(name = "entrada")
    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    @Column(name = "valorTotal")
    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Column(name = "valorTotalMO")
    public double getValorTotalMO() {
        return valorTotalMO;
    }

    public void setValorTotalMO(double valorTotalMO) {
        this.valorTotalMO = valorTotalMO;
    }

    @Column(name = "valorTotalPecas")
    public double getValorTotalPecas() {
        return valorTotalPecas;
    }

    public void setValorTotalPecas(double valorTotalPecas) {
        this.valorTotalPecas = valorTotalPecas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
    @Column(name="codOrcamento")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "codVeiculo")
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
