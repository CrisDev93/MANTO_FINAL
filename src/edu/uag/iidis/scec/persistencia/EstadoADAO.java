package edu.uag.iidis.scec.persistencia;

import org.hibernate.*;
import org.hibernate.type.*;
import org.hibernate.criterion.Example;
//import org.hibernate.classic.*;


import edu.uag.iidis.scec.excepciones.ExcepcionInfraestructura;
import edu.uag.iidis.scec.modelo.EstadoA;
import edu.uag.iidis.scec.persistencia.hibernate.HibernateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Collection;
import java.util.List;


public class EstadoADAO {

    private Log log = LogFactory.getLog(EstadoADAO.class);

    public EstadoADAO() {
    }


    public EstadoA buscarPorId(Long idEstadoA, boolean bloquear)
            throws ExcepcionInfraestructura {

        EstadoA estadoa = null;

        if (log.isDebugEnabled()) {
            log.debug(">buscarPorID(" + idEstadoA + ", " + bloquear + ")");
        }

        try {
            if (bloquear) {
                estadoa = (EstadoA)HibernateUtil.getSession()
                                                .load(EstadoA.class, 
                                                      idEstadoA, 
                                                      LockMode.UPGRADE);
            } else {
                estadoa = (EstadoA)HibernateUtil.getSession()
                                                .load(EstadoA.class,
                                                      idEstadoA);
            }
        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }

            throw new ExcepcionInfraestructura(ex);
        }
        return estadoa;
    }


    public Collection buscarTodos()
            throws ExcepcionInfraestructura {

        Collection estadosa;

        if (log.isDebugEnabled()) {
            log.debug(">buscarTodos()");
        }

        try {
            estadosa = HibernateUtil.getSession()
                                    .createCriteria(EstadoA.class)
                                    .list();
                                    
                                         
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        System.out.println("ESTO ESTOY APUNTO DE RETORNAR---->"+estadosa);
        return estadosa;
    }


    public Collection buscarPorEjemplo(EstadoA estadoa)
            throws ExcepcionInfraestructura {


        Collection estadosa;
 
        if (log.isDebugEnabled()) {
            log.debug(">buscarPorEjemplo()");
        }

        try {
            Criteria criteria = HibernateUtil.getSession()
                                             .createCriteria(EstadoA.class);
            estadosa = criteria.add(Example.create(estadoa)).list();
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
        return estadosa;
    }


    public void hazPersistente(EstadoA estadoa)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazPersistente(estadoa)");
        }

        try {
            HibernateUtil.getSession().saveOrUpdate(estadoa);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }


    public void hazTransitorio(EstadoA estadoa)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">hazTransitorio(estadoa)");
        }

        try {
            HibernateUtil.getSession().delete(estadoa);
        } catch (HibernateException e) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException");
            }
            throw new ExcepcionInfraestructura(e);
        }
    }
    public Collection buscarPorNombre(String nombreEstadoa)
            throws ExcepcionInfraestructura {

        Collection estadosretornar;


        try {
            
            String hql = "from EstadoA where nombre LIKE:nombre";
             
             if (log.isDebugEnabled()) {
                 log.debug(hql + nombreEstadoa);
            }
        
            Query query = HibernateUtil.getSession()
                                        .createQuery(hql);
            

            query.setParameter("nombre", "%"+nombreEstadoa+"%");

            
            if (log.isDebugEnabled()) {
                 log.debug("  ***query:   "+hql );
            }

            estadosretornar = query.list();
                System.out.println("VOY A RETORNAR DE BUSQUEDA----> "+estadosretornar);
            return estadosretornar;

        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException *******************"+ex);
            }
            throw new ExcepcionInfraestructura(ex);
        }
    }

    public boolean existeEstadoa(String nombreEstadoa)
            throws ExcepcionInfraestructura {

        if (log.isDebugEnabled()) {
            log.debug(">existeEstadoA(nombreEstadoa)");
        }

        try {
            
            
//            String consultaCuentaRoles =
//            "select count(*) from Ciudad r where r.nombre=?";
//
 //           int resultado =
 //           ((Integer) HibernateUtil.getSession()
 //                          .find(consultaCuentaRoles, 
 //                                nombreRol,
 //                                StringType.INSTANCE)
 //                          .iterator()
 //                          .next()).intValue();
// de acuerdo al nuevo formato
 
            String hql = "select nombre from EstadoA where nombre = :nombre";
            
             if (log.isDebugEnabled()) {
                 log.debug(hql + nombreEstadoa);
            }
        
            Query query = HibernateUtil.getSession()
                                        .createQuery(hql);
            if (log.isDebugEnabled()) {
                 log.debug("<<<<<<<<< create query ok " );
            }

            query.setParameter("nombre", nombreEstadoa);
            if (log.isDebugEnabled()) {
                 log.debug("<<<<<<<<< set Parameter ok antes del query list >>>>>");
            }
            List results = query.list();
            int resultado = results.size();
            if (log.isDebugEnabled()) {
                 log.debug("<<<<<<<<< Result size " + resultado);
            }
            if (resultado == 0) {
               return false;
            }
            
            return true;

        } catch (HibernateException ex) {
            if (log.isWarnEnabled()) {
                log.warn("<HibernateException *******************"+ ex);
            }
            throw new ExcepcionInfraestructura(ex);
        }
    }


}
