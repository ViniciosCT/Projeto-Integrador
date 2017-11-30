package br.ufsm.csi.seguranca.model;

import javax.persistence.*;

@Entity
@Table(name = "Veiculo")
public class Veiculo {

    private String placa;
    private Long ano;
    private String marca;
    private String combustivel;
    private String modelo;
    private String cor;
    private Long codigo;
    private Cliente cliente;

    //Gerados:
    @Column(name = "placa")
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Column(name = "ano")
    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    @Column(name = "marca")
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Column(name = "combustivel")
    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    @Column(name = "modelo")
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Column(name = "cor")
    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
    @Column(name="codVeiculo")
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    @ManyToOne
    @JoinColumn(name = "codCliente")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
