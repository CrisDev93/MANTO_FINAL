package edu.uag.iidis.scec.servicios;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import edu.uag.iidis.scec.modelo.Auto;
import edu.uag.iidis.scec.excepciones.*;
import edu.uag.iidis.scec.persistencia.AutoDAO;
import edu.uag.iidis.scec.persistencia.hibernate.*;

public class ManejadorAutos {
    private Log log = LogFactory.getLog(ManejadorRoles.class);
    private AutoDAO dao;

    public ManejadorAutos() {
        dao = new AutoDAO();
    }


    public Collection listarAutos() {
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

    public Collection listarAutosOrd(String tipo, int tipoInt) {

        Collection resultado;

        try {
            HibernateUtil.beginTransaction();
            resultado = dao.ordenarPor(tipo, tipoInt);
            HibernateUtil.commitTransaction();

              if (log.isDebugEnabled()) {
            log.debug("***Manejador autos ordenar: ok");
        }

            return resultado;         
       
        } catch (ExcepcionInfraestructura e) {
             if (log.isDebugEnabled()) {
            log.debug("***Manejador autos ordenar: failed");
        }
            HibernateUtil.rollbackTransaction();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public void eliminarAuto(Long id) {
        if (log.isDebugEnabled()) {
            log.debug(">eliminarAuto(persona)");
        }
        try {
            HibernateUtil.beginTransaction();           
            Auto persona = dao.buscarPorId(id, true);
            if (persona != null) {
              dao.hazTransitorio(persona);
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

    public int crearAuto(Auto persona) {

        int resultado;

        if (log.isDebugEnabled()) {
            log.debug(">guardarAuto(persona)");
        }

        try {
            HibernateUtil.beginTransaction();           
            
            if (dao.existeAuto(persona.getMarca())) {
               resultado = 1; // Excepción. El nombre de persona ya existe
            } else {

               dao.hazPersistente(persona);

               resultado = 0; // Exito. persona se creo satisfactoriamente.
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
