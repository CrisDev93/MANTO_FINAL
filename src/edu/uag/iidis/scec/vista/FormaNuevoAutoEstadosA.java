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
public final class FormaNuevoAutoEstadosA
        extends ValidatorForm {

    private Collection estadosa;
    private Collection paises;
    private int contador;
    private String estadosaBuscar;

    public void setEstadosa(Collection estadosa) {
        this.estadosa = estadosa;
        if (estadosa != null) {
          this.contador = estadosa.size();
        } else
          this.contador = -1;
    }

    public Collection getEstadosa() {
        return (this.estadosa);
    }

    public void setPaises(Collection paises) {
        this.paises = paises;
        if (paises != null) {
          this.contador = paises.size();
        } else
          this.contador = -1;
    }

    public Collection getPaises() {
        return (this.paises);
    }

    public void setEstadosaBuscar(String estadosaBuscarM) {
        System.out.println("ESTO LE ASIGNO A estadosaBuscar -----> "+estadosaBuscarM);
        this.estadosaBuscar= estadosaBuscarM;
    }

    public String getEstadosaBuscar() {
        return (this.estadosaBuscar);
    }


    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        estadosaBuscar=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
