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
public final class FormaListadoEstadosA
        extends ValidatorForm {

    public Collection paises;
    public int contador2;
    public Collection estadosa;
    public int contador;

    public void setPaises(Collection paises) {
        this.paises = paises;
        if (paises != null) {
          this.contador2 = paises.size();
        } else
          this.contador2 = -1;
    }

    public Collection getPaises() {
        return (this.paises);
    }

    public void setEstadosa(Collection estadosa) {
        this.estadosa = estadosa;
        if (estadosa != null) {
          this.contador = estadosa.size();
          System.out.println("ACAAAAAAAAAAAAAAAAAAAAAAAAA"+this.estadosa);
        } else
          this.contador = -1;
    }

    public Collection getEstadosa() {
        return (this.estadosa);
    }
  
    public int getContador() {
        return (this.contador);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        contador=0;
        estadosa=null;
        contador2=0;
        paises=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
