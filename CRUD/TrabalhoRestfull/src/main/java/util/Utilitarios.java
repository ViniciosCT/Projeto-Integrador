package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by v_c_t on 06/10/2017.
 */
public class Utilitarios {

    private static Connection c;

    public static Connection conexao(){

        if(c != null){
            return c;
        }else{
            try {

                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/GerenciadorQualitat";
                String user = "postgres";
                String senha = "17141307";

                c = DriverManager.getConnection(url, user, senha);
                return c;
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch(SQLException e){

                e.printStackTrace();
            }
            System.out.println("... conexao realizada ... ");
        }


        return null;

    }

}