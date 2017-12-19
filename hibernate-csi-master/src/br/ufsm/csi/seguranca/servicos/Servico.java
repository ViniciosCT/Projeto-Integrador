package br.ufsm.csi.seguranca.servicos;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Fase;
import br.ufsm.csi.seguranca.model.OSXFase;
import br.ufsm.csi.seguranca.model.OrdemServico;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Servico {

    public Duration tempoTotalLoja(OrdemServico ordemServico, HibernateDAO hibernateDAO){
        Duration tempoLoja = null;
        Map<String, String> m = new HashMap<>();
        m.put("nome", "");
        Collection<Object> faseLista = hibernateDAO.listaObjetos(Fase.class, m, null, null, false);
        for (Object faseObject : faseLista) {
            Fase fase = (Fase) faseObject;

            Duration tempoFase =  tempoFase( fase, ordemServico, hibernateDAO);

            if( tempoFase!=null ){
                if(tempoLoja==null){
                    tempoLoja = tempoFase;
                }else{
                    tempoLoja = tempoLoja.plus(tempoFase);
                }
            }
        }
        return tempoLoja;
    }

    public Duration tempoFase(Fase fase, OrdemServico os, HibernateDAO hibernateDAO){

        Map<String, Object> m2 = new HashMap<>();
        m2.put("fase", fase);
        m2.put("os", os);
        Collection<Object> osXFaseLista2 = hibernateDAO.listaObjetosEquals(OSXFase.class, m2);
        if (osXFaseLista2.toArray().length==0){
            return null;
        }

        Duration tempoFase = null;
        int i = 0;
        for (Object obj : osXFaseLista2){
            OSXFase relacaoCorreta = (OSXFase) obj;
            Duration tempoNaFase;
            Instant dataInicial = relacaoCorreta.getTempoInicial().toInstant();
            if(relacaoCorreta.getTempoFinal()==null){ //fase atual:

                Instant dataAgora = Instant.now();
                tempoNaFase = Duration.between(dataInicial , dataAgora);

            }else { //fase do historico:

                Instant dataFinal = relacaoCorreta.getTempoFinal().toInstant();
                tempoNaFase = Duration.between(dataInicial, dataFinal);

            }
            if(i==0){
                tempoFase = tempoNaFase;
            }else{
                tempoFase = tempoFase.plus(tempoNaFase);
            }
            i++;
        }
        tempoFase = descontaInatividade(tempoFase);
        return tempoFase;

    }

    private Duration descontaInatividade(Duration tempo) {
        long nDias = tempo.toDays();
        long nHoras = tempo.toHours();
        long nMinutos = tempo.toMinutes();
        long nSegundos = tempo.getSeconds();

        long totalHorasDias = (nDias * 24) - (nDias * 16);

        long restoDeHoras = (nHoras - (nDias * 24));
        long minutosValidos = 0;
        long segundosValidos = 0;
        if(restoDeHoras > 8){
            restoDeHoras = 8;
        }else{
            minutosValidos = (nMinutos - (nHoras * 60));
            segundosValidos = (nSegundos - (nMinutos * 60));
        }
        long horasTotais = restoDeHoras + totalHorasDias;
        long totalDeSegundos = ((horasTotais * 60) * 60) + (minutosValidos * 60) + segundosValidos;

        return Duration.ofSeconds(totalDeSegundos);
    }

    public ArrayList<Collection> geraRelatorioOsFase(HibernateDAO hibernateDAO){
        Collection<Collection> relatorio = new ArrayList<>();
        for (int i = 1; i <= 6; i++ ){
            Map<String, Object> m2 = new HashMap<>();
            Long codigo = new Long(i);
            Fase faseBanco = (Fase) hibernateDAO.carregaObjeto(Fase.class, codigo);
            m2.put("faseAtual", faseBanco );
            Collection<Object> OSsFase = hibernateDAO.listaObjetosEquals(OrdemServico.class, m2);
            relatorio.add(OSsFase);
        }
        return (ArrayList<Collection>) relatorio;
    }

    public ArrayList<Long> buscaQtdOSPorFase(HibernateDAO hibernateDAO){
        Collection<Long> qtdOSPorFase = new ArrayList<>();
        for (int i = 1; i <= 6; i++ ){
            Map<String, Object> m2 = new HashMap<>();
            Long codigo = new Long(i);
            Fase faseBanco = (Fase) hibernateDAO.carregaObjeto(Fase.class, codigo);
            m2.put("faseAtual", faseBanco );
            Collection<Object> OSsFase = hibernateDAO.listaObjetosEquals(OrdemServico.class, m2);
            Long qtdFase = new Long(OSsFase.size());
            qtdOSPorFase.add(qtdFase);
        }
        return (ArrayList<Long>) qtdOSPorFase;
    }

    public ArrayList<Double> buscaFaturamentoOSPorFase(HibernateDAO hibernateDAO){
        Collection<Double> faturamentoOSPorFase = new ArrayList<>();
        for (int i = 1; i <= 6; i++ ){
            Map<String, Object> m2 = new HashMap<>();
            Long codigo = new Long(i);
            Fase faseBanco = (Fase) hibernateDAO.carregaObjeto(Fase.class, codigo);
            m2.put("faseAtual", faseBanco );
            Collection<Object> OSsFase = hibernateDAO.listaObjetosEquals(OrdemServico.class, m2);
            double faturamento = 0;
            for (Object obj : OSsFase) {
                OrdemServico os = (OrdemServico) obj;
                faturamento = faturamento + os.getOrcamento().getValorTotal();
            }
            faturamentoOSPorFase.add(faturamento);
        }
        return (ArrayList<Double>) faturamentoOSPorFase;
    }

    public void trocaFaseService(HibernateDAO hibernateDAO, OrdemServico ordemServico, Fase fase){
        Map<String, Object> m = new HashMap<>();
        m.put("os", ordemServico );
        m.put("fase", ordemServico.getFaseAtual() );
        Collection<Object> osXFaseLista = hibernateDAO.listaObjetosEquals(OSXFase.class, m);

        OSXFase relacaoCorreta = new OSXFase();
        for(Object obj : osXFaseLista){
            OSXFase osxFase = (OSXFase) obj;
            if (osxFase.getTempoFinal()==null){
                relacaoCorreta = osxFase;
            }
        }
        Date dataFinal = new Date();
        relacaoCorreta.setTempoFinal(dataFinal);

        ordemServico.setFaseAtual(fase);

        OSXFase relacao = new OSXFase();
        relacao.setFase(fase);
        relacao.setOs(ordemServico);
        relacao.setTempoInicial(new Date());
        relacao.setTempoFinal(null);
        hibernateDAO.criaObjeto(relacao);
    }

    public String formataTempo(Duration tempo) {
        long nDias = tempo.toDays();
        long nHoras = tempo.toHours();
        long nMinuts = tempo.toMinutes();
        long nSeconds = tempo.getSeconds();
        return nDias + " " + (nHoras - (nDias * 24)) + ":" + (nMinuts - (nHoras * 60)) + ":" + (nSeconds - (nMinuts * 60));
    }
}
