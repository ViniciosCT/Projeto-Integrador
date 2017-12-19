package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Fase;
import br.ufsm.csi.seguranca.model.OrdemServico;
import br.ufsm.csi.seguranca.servicos.Servico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.Duration;
import java.util.*;

@Controller
public class FaseController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("trocaFase.html")
    public String pegaTrocaFasePage(Model model) {
        Servico servico = new Servico();
        Collection<Collection> relatorio = servico.geraRelatorioOsFase(hibernateDAO);
        Collection<Long> qtdOSPorFase = servico.buscaQtdOSPorFase(hibernateDAO);
        Collection<Double> faturamentoOSPorFase = servico.buscaFaturamentoOSPorFase(hibernateDAO);

        for (int i = 0; i <= 5; i++){
            model.addAttribute("listaFase" + (i+1), relatorio.toArray()[i] );
            model.addAttribute("qtdOSFase" + (i+1), qtdOSPorFase.toArray()[i] );
            model.addAttribute("faturamentoFase" + (i+1), faturamentoOSPorFase.toArray()[i]);
        }

        return "trocaFase";
    }

    @Transactional
    @RequestMapping( value = "trocaFaseSelecao.html", method = RequestMethod.GET)
    public String trocaFaseSelecao(Model model, Long codOrdemServico) {
        OrdemServico ordemServico  = (OrdemServico) hibernateDAO.carregaObjeto(OrdemServico.class , codOrdemServico);

        Servico servico = new Servico();
        Duration tempoLoja = servico.tempoTotalLoja(ordemServico, hibernateDAO);
        Duration tempoFaseAtual = servico.tempoFase(ordemServico.getFaseAtual(), ordemServico, hibernateDAO);

        Map<String, String> m = new HashMap<>();
        m.put("nome", "");
        Collection<Object> fasesLista = hibernateDAO.listaObjetos(Fase.class, m, null, null, false);

        model.addAttribute("tempoLoja", servico.formataTempo(tempoLoja));
        model.addAttribute("tempoFase", servico.formataTempo(tempoFaseAtual));
        model.addAttribute("ordemServico", ordemServico);
        model.addAttribute("fasesLista", fasesLista);

        return "trocaFaseSelecao";
    }

    @Transactional
    @RequestMapping("mudaFase.html")
    public String mudaFase(Long codFase, Long codOS) {
        OrdemServico ordemServico  = (OrdemServico) hibernateDAO.carregaObjeto(OrdemServico.class , codOS);
        Fase fase = (Fase) hibernateDAO.carregaObjeto(Fase.class, codFase);

        Servico servico = new Servico();
        servico.trocaFaseService(hibernateDAO, ordemServico, fase);

        return "redirect:trocaFase.html";
    }

}
