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
  function EliminarEstadoA(strEstadoAName){
    return confirm("¿Desea eliminar el estado '" + strEstadoAName + "'?")
  }
-->
</script>
    <br>
    <font size='5'><fmt:message key="formaListadoEstadosA.titulo" /></font>
    <div class="table" style="width:100%" >
        <div class="tr">
            <div class="td" colspan="4" style="padding-top:25px; padding-bottom:25px;">
                <a href="solicitarRegistroEstadoA.do" class="HipervinculoAdmon boton">Agregar nuevo Estado</a>
            </div>
        </div>
        <div class="tr">
            <div class="td" colspan="4">
               <html:errors />
            </div>
        </div>
        <div class="tr" style="background:#CCCCCC">
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoEstadosA.etiqueta.nombre" /></b></div>
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoEstadosA.etiqueta.pais" /></b></div>
         <div class="td" style="width:38%; border-right-style:solid; border-left-style:solid; border-width:1px; border-color:#000000;"><b><fmt:message key="formaListadoEstadosA.etiqueta.descripcion" /></b></div>
         <div class="td" style="width:20%"><b><fmt:message key="formaListadoEstadosA.etiqueta.administracion" /></b></div>
        </div>      


     


        <c:forEach var="estadoa" items="${formaListadoEstadosA.estadosa}"> 
            <div class="tr">      
        
                <div class="td" align="left" style="width:20%"><c:out value="${estadoa.nombre}"/></div>
                <div class="td" align="left" style="width:20%">
                  <c:forEach var="pais" items="${formaListadoEstadosA.paises}">
                 
                    <c:if test="${pais.id == estadoa.idPais}">
                      <c:out value="${pais.nombre}"/>
                    </c:if>
                  </c:forEach>
                  
                </div>
                <div class="td" align="left" style="width:38%"><c:out value="${estadoa.descripcion}"/></div>
                <div class="td" align="left" style="width:20%">
             <!--       <a href='solicitarModificarCiudad.do?id=<c:out value="${ciudad.id}"/>'
					   class="HipervinculoAdmon">
                        <fmt:message key="formaListadoCiudades.etiqueta.modificar" />
                    </a>-->
                    <a href='procesarEliminarEstadoA.do?id=<c:out value="${estadoa.id}"/>'
					   onClick="javascript: return EliminarEstadoA('<c:out value="${estadoa.nombre}"/>')"
					   class="HipervinculoAdmon">
                        <fmt:message key="formaListadoEstadosA.etiqueta.eliminar" />
                    </a>
                </div>
            </div>
        </c:forEach>
        <div class="tr">
            <div class="td" colspan="4" align="right" style="padding-top:25px;"><b>Total:</b> ${formaListadoEstadosA.contador}</div>
        </div>
    </div>