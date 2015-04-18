package edu.uag.iidis.scec.control;

import edu.uag.iidis.scec.vista.*;
import edu.uag.iidis.scec.modelo.*;
import edu.uag.iidis.scec.servicios.*;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;



public final class MCURegistrarAuto 
        extends MappingDispatchAction {

    private Log log = LogFactory.getLog(MCURegistrarUsuario.class);


    public ActionForward solicitarRegistroAuto(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        FormaNuevoAuto forma = (FormaNuevoAuto)form;

        ManejadorEstadosA mr = new ManejadorEstadosA();
        Collection resultado = mr.listarEstadosA();

        forma.setEstadosa( resultado );

        if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistroAuto");
        }

        return (mapping.findForward("exito"));
    }

    public ActionForward procesarRegistroAutoEstadosA(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        FormaNuevoAutoEstadosA forma = (FormaNuevoAutoEstadosA)form;

        ManejadorEstadosA mr = new ManejadorEstadosA();
        if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistroAutoEstadosA: buscando:"+forma.getestadosaBuscar());
        }
        System.out.println("forma.getestadosaBuscar() RETORNA ->");

        Collection resultado = mr.buscarEstadosA("Michoacan");


        ManejadorPaises mr2 = new ManejadorPaises();
        Collection resultado2 = mr2.listarPaises();
        if(resultado==null||resultado2==null){
          if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistroAutoEstadosA:*** resultado nulo");
        }}else{
         if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistroAutoEstadosA:*** resultado ok");
        }

        }
        System.out.println("MCURegistrarAuto envía en forma.setEstadosa -> "+resultado);

        System.out.println("MCURegistrarAuto envía en forma.setPaises -> "+resultado2);
        forma.setEstadosa( resultado );
        forma.setPaises( resultado2 );

        if (log.isDebugEnabled()) {
            log.debug(">solicitarRegistroAutoEstadosA");
        }

        return (mapping.findForward("exito"));
    }


    public ActionForward procesarRegistroAuto(
                ActionMapping mapping,
                ActionForm form,
                HttpServletRequest request,
                HttpServletResponse response)
            throws Exception {

        if (log.isDebugEnabled()) {
            log.debug(">procesarRegistroAuto");
        }

        // Verifica si la acción fue cancelada por el usuario
        if (isCancelled(request)) {
            if (log.isDebugEnabled()) {
                log.debug("<La acción fue cancelada");
            }
            return (mapping.findForward("cancelar"));
        }

        
        // Se obtienen los datos para procesar el registro
        FormaNuevoAuto forma = (FormaNuevoAuto)form;

        Auto per = new Auto(forma.getMarca(),
                          forma.getColor(), forma.getPlacas(), forma.getPropietario(), forma.getidEstadoA());

        ManejadorAutos mr = new ManejadorAutos();
        int resultado = mr.crearAuto(per);

        ActionMessages errores = new ActionMessages();
        switch (resultado) {
            case 0:   
                return (mapping.findForward("exito"));

            case 1:
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.nombrePersonaYaExiste",
                                               forma.getMarca()));                
                saveErrors(request, errores);
                return (mapping.getInputForward());

            case 3:
                log.error("Ocurrió un error de infraestructura");
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.infraestructura"));                
                saveErrors(request, errores);
                return (mapping.getInputForward());

            default:
                log.warn("ManejadorUsuario.crearUsuario regresó reultado inesperado");
                errores.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.infraestructura"));                
                saveErrors(request, errores);
                return (mapping.getInputForward());
        }
    }

}

