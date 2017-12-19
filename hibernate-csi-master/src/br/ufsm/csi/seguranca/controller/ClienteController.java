package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    public String criaCliente(Cliente cliente){
        System.out.println("Chamou AQ");
        if (cliente.getCodigo() == null) {
            cliente.setDataCadastro(new Date());
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
        Collection<Object> clientesLista = hibernateDAO.listaObjetos(Cliente.class, m, null, null, false);
        model.addAttribute("clientes", clientesLista);
        return "clientes";
    }

}
