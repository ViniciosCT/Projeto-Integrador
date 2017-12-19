package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Cliente;
import br.ufsm.csi.seguranca.model.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cpol on 22/05/2017.
 */
@Controller
public class VeiculosController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("veiculos.html")
    public String getVeiculosPage() {
        return "redirect:listaVeiculos.html";
    }

    @Transactional
    @RequestMapping("novoVeiculo.html")
    public String getNovoVeiculoPage(Model model) {
        Map<String, String> m = new HashMap<>();
        m.put("nome", "");
        Collection<Object> clientesLista = hibernateDAO.listaObjetos(Cliente.class, m, null, null, false);
        model.addAttribute("clientes", clientesLista);
        return "novoVeiculo";
    }

    @Transactional
    @RequestMapping(value = "criaVeiculo.html", method = RequestMethod.POST)
    public String criaVeiculo(Veiculo veiculo, Long codCliente) {
        Cliente cliente  = (Cliente) hibernateDAO.carregaObjeto(Cliente.class , codCliente);
        veiculo.setCliente(cliente);
        if (veiculo.getCodigo() == null) {
            hibernateDAO.criaObjeto(veiculo);
        } else {

            Veiculo veiculoPersistente = (Veiculo) hibernateDAO.carregaObjeto(Veiculo.class, veiculo.getCodigo());
            veiculoPersistente.setPlaca(veiculo.getPlaca());
            veiculoPersistente.setAno(veiculo.getAno());
            veiculoPersistente.setMarca(veiculo.getMarca());
            veiculoPersistente.setCombustivel(veiculo.getCombustivel());
            veiculoPersistente.setModelo(veiculo.getModelo());
            veiculoPersistente.setCor(veiculo.getCor());
            veiculoPersistente.setCodigo(veiculo.getCodigo());
            veiculoPersistente.setCliente(veiculo.getCliente());

        }

        return "redirect:listaVeiculos.html";
    }

    @Transactional
    @RequestMapping("listaVeiculos.html")
    public String listaVeiculos(Model model) {
        Map<String, String> m = new HashMap<>();
        m.put("placa", "");
        Collection<Object> veiculosLista = hibernateDAO.listaObjetos(Veiculo.class, m, null, null, false);
        model.addAttribute("veiculos", veiculosLista);
        return "veiculos";
    }

    @Transactional
    @RequestMapping("alterarVeiculo.html")
    public String getAlterarVeiculoPage(Model model, Veiculo veiculo, Long codCliente) {
        Cliente cliente  = (Cliente) hibernateDAO.carregaObjeto(Cliente.class , codCliente);
        veiculo.setCliente(cliente);
        model.addAttribute(veiculo);
        return "alterarVeiculo";
    }
}
