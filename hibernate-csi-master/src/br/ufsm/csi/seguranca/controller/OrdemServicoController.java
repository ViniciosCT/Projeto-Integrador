package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.*;
import br.ufsm.csi.seguranca.servicos.Servico;
//import org.joda.time.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Duration;
import java.util.Collection;
//import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpol on 22/05/2017.
 */
@Controller
public class OrdemServicoController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("os.html")
    public String getOsPage() {
        return "redirect:listaOrdemServico.html";
    }

    @Transactional
    @RequestMapping("listaOrdemServico.html")
    public String listaOrdemServico(Model model, HibernateDAO hibernateDAO) {
        Map<String, String> m = new HashMap<>();
        m.put("descricaoReparos", "");
        if(this.hibernateDAO==null){
            Collection<Object> ordemServicoLista = hibernateDAO.listaObjetos(OrdemServico.class, m, null, null, false);
            model.addAttribute("ordensServicos", ordemServicoLista);
        }else{
            Collection<Object> ordemServicoLista = this.hibernateDAO.listaObjetos(OrdemServico.class, m, null, null, false);
            model.addAttribute("ordensServicos", ordemServicoLista);
        }
        return "os";
    }

    @Transactional
    @RequestMapping(value = "descricaoOS.html", method = RequestMethod.GET)
    public String descricaoOS(Model model, Long codOrdemServico){
        OrdemServico ordemServico  = (OrdemServico) hibernateDAO.carregaObjeto(OrdemServico.class , codOrdemServico);

        Servico servico = new Servico();
        Duration tempoLoja = servico.tempoTotalLoja(ordemServico, hibernateDAO);
        Duration tempoFaseAtual = servico.tempoFase(ordemServico.getFaseAtual(), ordemServico, hibernateDAO);

        model.addAttribute("tempoLoja", servico.formataTempo(tempoLoja));
        model.addAttribute("tempoFase", servico.formataTempo(tempoFaseAtual));
        model.addAttribute("ordemServico", ordemServico);
        return "descricaoOS";
    }

    @Transactional
    @RequestMapping(value = "finalizaOS.html", method = RequestMethod.GET)
    public String finalizaOS(Long codOrdemServico){
        OrdemServico ordemServico  = (OrdemServico) hibernateDAO.carregaObjeto(OrdemServico.class , codOrdemServico);

        Map<String, Object> m2 = new HashMap<>();
        m2.put("os", ordemServico );
        Collection<Object> osXFases = hibernateDAO.listaObjetosEquals(OSXFase.class, m2);

        for (Object obj: osXFases) {
            OSXFase osXFase = (OSXFase) obj;
            hibernateDAO.removeObjeto(osXFase);
        }

        hibernateDAO.removeObjeto(ordemServico);
        return "redirect:listaOrdemServico.html";
    }
}
