package edu.uag.iidis.scec.vista;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;


/**
 * Form bean para el registro de una nueva persona.
 *
 * @author Victor Ramos
 */
public final class FormaListadoAutos
        extends ValidatorForm {

    public Collection estadosa;
    private int contador2;
    public Collection autos;
    private int contador;
    
    public void setEstadosa(Collection estadosa) {
        this.estadosa = estadosa;
        if (estadosa != null) {
          this.contador2 = estadosa.size();
        } else
          this.contador2 = -1;
    }

    public Collection getEstadosa() {
        return (this.estadosa);
    }

    public void setAutos(Collection autos) {
        this.autos = autos;
        if (autos != null) {
          this.contador = autos.size();
        } else
          this.contador = -1;
    }

    public Collection getAutos() {
        return (this.autos);
    }
  
    public int getContador() {
        return (this.contador);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        contador=0;
        autos=null;
        contador2=0;
        estadosa=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
