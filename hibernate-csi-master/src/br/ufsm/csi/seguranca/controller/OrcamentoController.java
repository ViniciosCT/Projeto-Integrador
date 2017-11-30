package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Orcamento;
import br.ufsm.csi.seguranca.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpol on 22/05/2017.
 */
@Controller
public class OrcamentoController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("orcamento.html")
    public String getOrcamentoPage() {
        return "redirect:listaOrcamentos.html";
    }

    @Transactional
    @RequestMapping("listaOrcamentos.html")
    public String listaOrcamentos(Model model) {
        Map<String, String> m = new HashMap<>();
        m.put("descricaoreparos", "");
        Collection<Object> orcamentosLista = hibernateDAO.listaObjetos(Orcamento.class, m, null, null, false);
        model.addAttribute("orcamentos", orcamentosLista);
        return "orcamento";
    }

}
