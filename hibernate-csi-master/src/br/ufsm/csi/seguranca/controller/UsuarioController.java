package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by cpol on 29/05/2017.
 */
@Controller
public class UsuarioController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, Model model, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        Map<String, Object> map = new HashMap<>();
        map.put("login", login);
        map.put("senha", senha);
        Collection funcionarios = hibernateDAO.listaObjetosEquals(Funcionario.class, map);
        if (funcionarios == null || funcionarios.isEmpty()) {
            model.addAttribute("msgDoServidor", "acesso-negado");
            return "../../index";
        } else {
            System.out.println("Nome: " + ((Funcionario) funcionarios.toArray()[0]).getNome() );
            session.setAttribute("usuarioLogado", ((Funcionario) funcionarios.toArray()[0]) );
            return "paginaInicial";
        }

    }

    @Transactional
    @RequestMapping("sair.html")
    public String sair(HttpSession session) {
        session.removeAttribute("usuarioLogado");
        return "../../index";
    }
//
//    @Transactional
//    @RequestMapping("cria-usuario.html")
//    public String criaUsuario(Usuario usuario, String senhaStr) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//        MessageDigest md = MessageDigest.getInstance("SHA-256");
//        usuario.setSenha(md.digest(senhaStr.getBytes("ISO-8859-1")));
//        hibernateDAO.criaObjeto(usuario);
//        return "exemplos/usuario";
//    }
////
////    @Transactional
////    @RequestMapping("login.html")
////    public String login(String login, String senha) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//////        Map<String, Object> map = new HashMap<>();
//////        map.put("login", login);
//////        MessageDigest md = MessageDigest.getInstance("SHA-256");
//////        map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));
//////        Collection usuarios = hibernateDAO.listaObjetosEquals(Usuario.class, map);
//////        if (usuarios == null || usuarios.isEmpty()) {
//////            return "acesso-negado";
//////        } else {
//////            return "ok";
//////        }
////        Usuario usuario = hibernateDAO.findUsuarioHQL(login, senha);
////        if (usuario == null) {
////            return "acesso-negado";
////        } else {
////            return "ok";
////        }
////    }
//
//    @Transactional
//    @RequestMapping("cria-log.priv")
//    public String criaLog(Long idUsuario,
//                          Long idObjeto,
//                          String classe,
//                          @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date dataHora) throws ClassNotFoundException {
//        Usuario usuario = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuario);
//        Log log = new Log();
//        log.setClasse(Class.forName(classe));
//        log.setIdObjeto(idObjeto);
//        log.setDataHora(dataHora);
//        log.setUsuario(usuario);
//        hibernateDAO.criaObjeto(log);
//        return "exemplos/log";
//    }
//
//    @Transactional
//    @RequestMapping("lista-usuarios.html")
//    public String listaUsuarios(Model model, String nome, String login) {
//        Map<String, String> m = new HashMap<>();
//        if (nome != null && !nome.isEmpty()) {
//            m.put("nome", nome);
//        }
//        if (login != null && !login.isEmpty()) {
//            m.put("login", login);
//        }
//        model.addAttribute("usuarios", hibernateDAO.listaObjetos(Usuario.class, m, null, null, false));
//        return "exemplos/lista-usuarios";
//    }

}
