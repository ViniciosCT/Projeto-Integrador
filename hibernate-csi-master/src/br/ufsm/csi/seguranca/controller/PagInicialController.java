package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Orcamento;
import br.ufsm.csi.seguranca.model.OrdemServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cpol on 22/05/2017.
 */
@Controller
public class PagInicialController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("paginaInicial.html")
    public String getPagInicialPage(Model model) {


        model.addAttribute("faturamento", hibernateDAO.faturamento("valorTotal"));
        model.addAttribute("qtdVeiculos", hibernateDAO.conta(OrdemServico.class));

        System.out.println("Valor total: " + hibernateDAO.faturamento("valorTotal"));
        System.out.println("Quantidade total: " + hibernateDAO.conta(OrdemServico.class));

        return "paginaInicial";
    }

}
