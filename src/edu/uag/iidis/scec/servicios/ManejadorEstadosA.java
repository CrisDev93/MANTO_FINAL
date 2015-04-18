package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.EstadoA;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.EstadoADAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorEstadosA {
    private Log log = LogFactory.getLog(ManejadorEstadosA.class);
    private EstadoADAO dao;

    public ManejadorEstadosA() {
        dao = new EstadoADAO();
    }


    public Collection listarEstadosA() {
        Collection resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarUsuario(usuario)");
        }

        try {
            HibernateUtil.beginTransaction();
            resultado = dao.buscarTodos();
            HibernateUtil.commitTransaction();
            return resultado;         
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public Collection buscarEstadosA(String estadoaBuscar) {
        Collection resultado;

        if (log.isDebugEnabled()) {
            log.debug(">***Buscar EstadoA:"+estadoaBuscar);
        }

        try {
            HibernateUtil.beginTransaction();
            resultado = dao.buscarPorNombre(estadoaBuscar);
            HibernateUtil.commitTransaction();
            return resultado;         
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public void eliminarEstadoA(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarEstadoA(estadoa)");
        }
        try {
            HibernateUtil.beginTransaction();           
            EstadoA estadoa = dao.buscarPorId(id, true);
            if (estadoa != null) {
              dao.hazTransitorio(estadoa);
            }
            HibernateUtil.commitTransaction();
        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();
            if (log.isWarnEnabled()) {
                log.warn("<ExcepcionInfraestructura");
            }
        } finally {
            HibernateUtil.closeSession();
        }

    }

    public int crearEstadoA(EstadoA estadoa) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarEstadoA(estadoa)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeEstadoa(estadoa.getNombre())) {
               resultado = 1; // Excepción. El nombre de ciudad ya existe
            } else {
                System.out.println("ESTO VOY A ENVIAAAAR --->"+ estadoa.getNombre()+'\n'+"PAIS --->"+estadoa.getidPais());
               dao.hazPersistente(estadoa);

               resultado = 0; // Exito. El ciudad se creo satisfactoriamente.
            }

            HibernateUtil.commitTransaction();

        } catch (ExcepcionInfraestructura e) {
            HibernateUtil.rollbackTransaction();

            if (log.isWarnEnabled()) {
                log.warn("<ExcepcionInfraestructura");
            }
            resultado = 2;    // Excepción. Falla en la infraestructura
        } finally {
            HibernateUtil.closeSession();
        }
        return resultado;
    }    
}
