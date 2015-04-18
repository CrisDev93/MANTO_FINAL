    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  .HipervinculoAdmon{
    color:#000000;
	text-decoration:none;
  }
  
  .HipervinculoAdmon:hover{
    color:#006666;
	text-decoration:underline;
  }
</style>
<script language="javascript" type="text/javascript">
<!--
  function EliminarAuto(PersonaName){
    return confirm("¿Desea eliminar el auto '" + PersonaName + "'?")
  }
-->
</script>
    <br>
    <font size='5'><fmt:message key="formaListadoAutos.titulo" /></font>
   
      <div class="table" style="width:100%"  id="tablecontent">
          <div class="tr">
              <div class="td" colspan="4" style="padding-top:25px; padding-bottom:25px;">
                  <a href="solicitarRegistroAuto.do" class="HipervinculoAdmon boton">Agregar nuevo Auto</a>
              </div>
          </div>
          <div class="tr">
              <div class="td" colspan="4">
                 <html:errors />
              </div>
          </div>
          <div class="tr enlaces" style="background:#CCCCCC">
           <div class="td" style="width:16%"><b id="marca" onclick="marca()">
            <fmt:message key="formaListadoAutos.etiqueta.marca" /></b></div>
           <div class="td" style="width:16%"><b id="color" onclick="color()">
            <fmt:message key="formaListadoAutos.etiqueta.color" /></b></div>
           <div class="td" style="width:16%"><b id="placas" onclick="placas()">
            <fmt:message key="formaListadoAutos.etiqueta.placas" /></b></div>
           <div class="td" style="width:16%"><b id="propietario" onclick="propietario()">
            <fmt:message key="formaListadoAutos.etiqueta.propietario" /></b></div>
           <div class="td" style="width:16%"><b id="estadoa" onclick="estadoA()">
            <fmt:message key="formaListadoAutos.etiqueta.estadoa" /></b></div>
           
           <div class="td"  style="width:16%"><b><fmt:message key="formaListadoAutos.etiqueta.administracion" /></b></div>
          </div>
          <c:forEach var="auto" items="${formaListadoAutos.autos}">
              <div class="tr">
                  <div class="td" align="left" style="width:16%"><c:out value="${auto.marca}"/></div>
                  <div class="td" align="left" style="width:16%"><c:out value="${auto.color}"/></div>
                  <div class="td" align="left" style="width:16%"><c:out value="${auto.placas}"/></div>
                  <div class="td" align="left" style="width:16%"><c:out value="${auto.propietario}"/></div>
                  <div class="td" align="left" style="width:16%">

                    <c:forEach var="estadoa" items="${formaListadoAutos.estadosa}">
                                      <c:if test="${auto.idEstado == estadoa.id}">
                     
                      <c:out value="${estadoa.nombre}"/>
                    </c:if>                         
                                    
                      
                    </c:forEach>
                  </div>
                  <div class="td" align="left" style="width:16%">
               <!--       <a href='solicitarModificarPersona.do?id=<c:out value="${persona.id}"/>'
               class="HipervinculoAdmon">
                          <fmt:message key="formaListadoPersonas.etiqueta.modificar" />
                      </a>-->
                      <a href='procesarEliminarAuto.do?id=<c:out value="${auto.id}"/>'
               onClick="javascript: return EliminarAuto('<c:out value="${auto.marca}"/>')"
               class="HipervinculoAdmon">
                          <fmt:message key="formaListadoAutos.etiqueta.eliminar" />
                      </a>
                  </div>
              </div>
          </c:forEach>
          <div class="tr">
              <div class="td" colspan="4" align="right" style="padding-top:25px;"><b>Total:</b> ${formaListadoAutos.contador}</div>
          </div>
      </div>
      