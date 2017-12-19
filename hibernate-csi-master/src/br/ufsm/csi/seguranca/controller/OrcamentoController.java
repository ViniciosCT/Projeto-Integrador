package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
        m.put("descricaoReparos", "");
        Collection<Object> orcamentosLista = hibernateDAO.listaObjetos(Orcamento.class, m, null, null, false);
        model.addAttribute("orcamentos", orcamentosLista);
        return "orcamento";
    }

    @Transactional
    @RequestMapping("novoOrcamento.html")
    public String getNovoOrcamentoPage(Model model) {
        Map<String, String> m = new HashMap<>();
        m.put("placa", "");
        Collection<Object> veiculosLista = hibernateDAO.listaObjetos(Veiculo.class, m, null, null, false);
        model.addAttribute("veiculos", veiculosLista);
        return "novoOrcamento";
    }

    @Transactional
    @RequestMapping(value = "criaOrcamento.html", method = RequestMethod.POST)
    public String criaOrcamento(Orcamento orcamento, Long codVeiculo, Model model) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        Veiculo veiculo  = (Veiculo) hibernateDAO.carregaObjeto(Veiculo.class , codVeiculo);
        double valorTotal = orcamento.getValorTotalMO() + orcamento.getValorTotalPecas();
        orcamento.setVeiculo(veiculo);
        orcamento.setValorTotal(valorTotal);
        orcamento.setEntrada(new Date());
        hibernateDAO.criaObjeto(orcamento);

        model.addAttribute(orcamento);

        return "novoOrcamentoOS";
    }

    @Transactional
    @RequestMapping(value = "transformaOrcamentoEmOS.html", method = RequestMethod.POST)
    public String transformaOrcamentoEmOS(boolean gerarOS, Long codOrcamento, Model model){
        if (gerarOS){
            Orcamento orcamento = (Orcamento) hibernateDAO.carregaObjeto(Orcamento.class , codOrcamento);
            Long id = Long.valueOf(1);
            Fase fase  = (Fase) hibernateDAO.carregaObjeto(Fase.class , id );

            OrdemServico os = new OrdemServico();
            os.setDataEntrada(new Date());
            os.setDescricaoReparos(orcamento.getDescricaoReparos());
            os.setFaseAtual(fase);
            os.setOrcamento(orcamento);
            hibernateDAO.criaObjeto(os);

            OSXFase relacao = new OSXFase();
            relacao.setFase(fase);
            relacao.setOs(os);
            relacao.setTempoInicial(new Date());
            relacao.setTempoFinal(null);
            hibernateDAO.criaObjeto(relacao);

            String retorno = new OrdemServicoController().listaOrdemServico(model, hibernateDAO);
            return retorno;
        }
        return "redirect:listaOrcamentos.html";
    }

    @Transactional
    @RequestMapping(value = "descricaoOrcamento.html", method = RequestMethod.GET)
    public String pegaDescricaoOrcamentoPage(Model model, Long codOrcamento){
        Orcamento orcamento  = (Orcamento) hibernateDAO.carregaObjeto(Orcamento.class , codOrcamento);
        model.addAttribute("orcamento", orcamento);
        return "descricaoOrcamento";
    }

}
