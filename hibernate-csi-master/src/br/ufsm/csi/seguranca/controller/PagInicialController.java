package br.ufsm.csi.seguranca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cpol on 22/05/2017.
 */
@Controller
public class PagInicialController {

    @Transactional
    @RequestMapping("paginaInicial.html")
    public String getPagInicialPage() {
        return "paginaInicial";
    }

}
