package dao;

import model.Cliente;
import util.Utilitarios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class ClienteDAO {

    public boolean novoCliente(Cliente cli){

        String sql = "insert into cliente (nome, email, telefone) values(?,?,?)";
        try{

            PreparedStatement pre = Utilitarios.conexao().prepareStatement(sql);
            pre.setString(1, cli.getNome());
            pre.setString(2, cli.getEmail() );
            pre.setString(3, cli.getTelefone());
            pre.execute();

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean atualizaCliente(Cliente cli){

        String sql = "UPDATE cliente set nome=?, email=?, telefone=? WHERE codcliente=?";
        try{

            PreparedStatement pre = Utilitarios.conexao().prepareStatement(sql);
            pre.setString(1, cli.getNome());
            pre.setString(2, cli.getEmail() );
            pre.setString(3, cli.getTelefone());
            pre.setInt(4, cli.getCodigo());
            pre.execute();

            return true;

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public ArrayList<Cliente> getClientes(){
        // vai fazer um select * from cliente
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

        Connection con = Utilitarios.conexao();
        String sql = "Select * from cliente";

        try {
            PreparedStatement preStmt = con.prepareStatement(sql);
            ResultSet rs =	preStmt.executeQuery();

            while(rs.next()){
                //Dados:
                int codcliente = rs.getInt("codcliente");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                //montar obj aluno
                Cliente cli = new Cliente();
                cli.setNome(nome);
                cli.setTelefone(telefone);
                cli.setEmail(email);
                cli.setCodigo(codcliente);

                listaClientes.add(cli);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes;
    }

    public Cliente getCliente(int codigo){
        Connection con = Utilitarios.conexao();
        String sql = "Select * from cliente WHERE codcliente=" + codigo;

        Cliente cli = new Cliente();
        try {
            PreparedStatement preStmt = con.prepareStatement(sql);
            ResultSet rs =	preStmt.executeQuery();
            rs.next();

            //Dados:
            int codcliente = rs.getInt("codcliente");
            String nome = rs.getString("nome");
            String email = rs.getString("email");
            String telefone = rs.getString("telefone");

            //montar obj aluno
            cli.setNome(nome);
            cli.setTelefone(telefone);
            cli.setEmail(email);
            cli.setCodigo(codcliente);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cli;
    }

    public boolean deleteCliente(int codigo){
        Connection c = Utilitarios.conexao();
        String sql = "DELETE FROM cliente where codcliente=?";

        try {
            PreparedStatement pst = c.prepareStatement(sql);
            pst.setInt(1, codigo);
            pst.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String[] args){

        Cliente cli = new Cliente();

        cli.setCodigo(001);
        cli.setEmail("vini@gmail.com");
        cli.setNome("Vinicios");
        cli.setTelefone("5598");

        ClienteDAO clidao = new ClienteDAO();

        clidao.novoCliente(cli);

        //clidao.atualizaCliente(cli);

        //System.out.println(clidao.getCliente(1).getNome());

        //clidao.deleteCliente(1);

    }

}
