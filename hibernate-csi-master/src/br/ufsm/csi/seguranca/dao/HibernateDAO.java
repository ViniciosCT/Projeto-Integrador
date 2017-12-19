package br.ufsm.csi.seguranca.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Created by cpol on 31/05/2017.
 */
@Repository
public class HibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void criaObjeto(Object o) {
        sessionFactory.getCurrentSession().save(o);
    }

    public void removeObjeto(Object o) {
        sessionFactory.getCurrentSession().remove(o);
    }

    public Number faturamento(){

        return (Number) sessionFactory.getCurrentSession().createQuery("select sum(ordem.orcamento.valorTotal) " +
                "from br.ufsm.csi.seguranca.model.OrdemServico as ordem ").list().get(0);
    }

    public Number conta(Class classe){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classe);
        detachedCriteria.setProjection(Projections.rowCount());
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        return (Number) criteria.uniqueResult();
    }

    public Collection<Object> listaObjetos(Class classe,
                                           Map<String, String> likeMap,
                                           Integer maxResults,
                                           String propOrdem,
                                           boolean asc) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classe);
        detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Map.Entry<String, String> ent : likeMap.entrySet()) {
            detachedCriteria.add(Restrictions.ilike(ent.getKey(), "%" + ent.getValue() + "%"));
        }
        if (propOrdem != null) {
            if (asc) {
                detachedCriteria.addOrder(Order.asc(propOrdem));
            } else {
                detachedCriteria.addOrder(Order.desc(propOrdem));
            }
        }
        Criteria criteria = detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        if (maxResults != null) {
            criteria.setMaxResults(maxResults);
        }
        return criteria.list();
    }

    public Collection<Object> listaObjetosEquals(Class classe, Map<String, Object> equalsMap) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classe);
        detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        for (Map.Entry<String, Object> ent : equalsMap.entrySet()) {
            detachedCriteria.add(Restrictions.eq(ent.getKey(), ent.getValue()));
        }

        return detachedCriteria.getExecutableCriteria(sessionFactory.getCurrentSession()).list();
    }

    public Object carregaObjeto(Class classe, Serializable id) {
        return sessionFactory.getCurrentSession().get(classe, id);
    }

}
