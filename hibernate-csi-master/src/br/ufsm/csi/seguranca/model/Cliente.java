package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="Cliente")
public class Cliente {

    private String nome;
    private Date dataCadastro;
    private String telefone;
    private String email;
    private int codigo;
    private Collection<Veiculo> veiculos;

    //Gerados:
    @Column(name="nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name="cadastro")
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Column(name="Telefone")
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name="Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario")
    @Column(name="codigo")
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @OneToMany(mappedBy = "cliente")
    public Collection<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Collection<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
