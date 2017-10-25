package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ClienteController {


    @Autowired
    private HibernateDAO hibernateDAO;

    /*
    @Transactional
    @RequestMapping(value = "clienteNovo", method = RequestMethod.POST)
    public String criaCliente(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("Chamou AQ");
        hibernateDAO.criaObjeto(cliente);
        return "Cliente";
    }
    */

    @RequestMapping(value = "cliente-novo.html")
    public String criaCliente()  {
        System.out.println("Chamou AQ");
        return "hello";
    }

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        Map<String, Object> map = new HashMap<>();
//        map.put("login", login);
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));
//        Collection usuarios = hibernateDAO.listaObjetosEquals(Usuario.class, map);
//        if (usuarios == null || usuarios.isEmpty()) {
//            return "acesso-negado";
//        } else {
//            return "ok";
//        }
        Usuario usuario = hibernateDAO.findUsuarioHQL(login, senha);
        if (usuario == null) {
            return "acesso-negado";
        } else {
            return "ok";
        }
    }

    @Transactional
    @RequestMapping("cria-log.priv")
    public String criaLog(Long idUsuario,
                          Long idObjeto,
                          String classe,
                          @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date dataHora) throws ClassNotFoundException {
        Usuario usuario = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuario);
        Log log = new Log();
        log.setClasse(Class.forName(classe));
        log.setIdObjeto(idObjeto);
        log.setDataHora(dataHora);
        log.setUsuario(usuario);
        hibernateDAO.criaObjeto(log);
        return "log";
    }

    @Transactional
    @RequestMapping("lista-usuarios.html")
    public String listaUsuarios(Model model, String nome, String login) {
        Map<String, String> m = new HashMap<>();
        if (nome != null && !nome.isEmpty()) {
            m.put("nome", nome);
        }
        if (login != null && !login.isEmpty()) {
            m.put("login", login);
        }
        model.addAttribute("usuarios", hibernateDAO.listaObjetos(Usuario.class, m, null, null, false));
        return "lista-usuarios";
    }
/*
    public static void main(String[] args){
        Cliente cli = new Cliente();

        cli.setEmail("vinicios@gmail.com");
        cli.setNome("Vinicios Tomazetti");
        cli.setTelefone("5596037050");

        ClienteController cc = new ClienteController();

        String gerado = null;
        try {
            //gerado = cc.criaCliente(cli);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(gerado);
    }
    */

}
