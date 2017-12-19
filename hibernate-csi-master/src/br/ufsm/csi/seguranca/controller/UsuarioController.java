package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.OrdemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collection;
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
    public String login(String login, String senha, Model model, HttpSession session){

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
            model.addAttribute("faturamento", hibernateDAO.faturamento());
            model.addAttribute("qtdVeiculos", hibernateDAO.conta(OrdemServico.class));

            return "paginaInicial";
        }

    }

    @Transactional
    @RequestMapping("sair.html")
    public String sair(HttpSession session) {
        session.removeAttribute("usuarioLogado");
        return "../../index";
    }

}
