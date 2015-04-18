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
public final class FormaNuevoEstadoA
        extends ValidatorForm {

    private Collection paises;
    private int contador;
    private String nombre;
    private String descripcion;
    private Long idPais;


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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return (this.nombre);
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return (this.descripcion);
    }

    public void setidPais(Long id) {
        this.idPais = id;
    }

    public Long getidPais() {
        return (this.idPais);
    }

    public void reset(ActionMapping mapping,
                      HttpServletRequest request) {
        nombre=null;
        descripcion=null;
        idPais=null;
    }


    public ActionErrors validate(ActionMapping mapping,
                                 HttpServletRequest request) {

        // Ejecuta las validaciones proporcionadas por Struts-Validator
        ActionErrors errores = super.validate(mapping, request);

        // Validaciones no cubiertas por Struts-Validator

        return errores;

    }

}
