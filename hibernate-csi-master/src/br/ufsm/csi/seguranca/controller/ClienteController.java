package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ClienteController {


    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("clientes.html")
    public String getClientePage() {
        return "redirect:listaClientes.html";
    }

    @Transactional
    @RequestMapping("novoCliente.html")
    public String getNovoClientePage() {
        return "novoCliente";
    }

    @Transactional
    @RequestMapping("alterarCliente.html")
    public String getAlterarClientePage(Model model, Cliente cliente) {
        model.addAttribute(cliente);
        return "alterarCliente";
    }

    @Transactional
    @RequestMapping(value = "criaCliente.html", method = RequestMethod.POST)
    public String criaCliente(Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.println("Chamou AQ");
        if (cliente.getCodigo() == null) {
            hibernateDAO.criaObjeto(cliente);
        } else {
            Cliente clientePersistente = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, cliente.getCodigo());
            clientePersistente.setTelefone(cliente.getTelefone());
            clientePersistente.setNome(cliente.getNome());
            clientePersistente.setCodigo(cliente.getCodigo());
            clientePersistente.setEmail(cliente.getEmail());
        }

        return "redirect:listaClientes.html";
    }

    @Transactional
    @RequestMapping("listaClientes.html")
    public String listaClientes(Model model) {
        Map<String, String> m = new HashMap<>();
        m.put("nome", "");
        model.addAttribute("clientes", hibernateDAO.listaObjetos(Cliente.class, m, null, null, false));
        return "clientes";
    }

    @Transactional
    @RequestMapping(value = "remove-cliente.html", method = RequestMethod.POST)
    public String removeCliente(Long codigo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Cliente cliente = (Cliente) hibernateDAO.carregaObjeto(Cliente.class, codigo);
        hibernateDAO.removeObjeto(cliente);
        return "Cliente";
    }


    @Transactional
    @RequestMapping("login2.html")
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


}
