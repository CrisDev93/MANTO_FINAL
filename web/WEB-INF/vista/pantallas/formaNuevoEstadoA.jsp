    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
    <%@ taglib uri="/WEB-INF/vista/etiquetas/struts-html.tld" prefix="html" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <br>
    <font size='5'><fmt:message key="formaNuevoEstadoA.titulo" /></font>

    <form id="forma" action="procesarRegistroEstadoA.do" method="post">
        <div class="table">
            <div class="tr">
                <div class="td" colspan="2">
                   <html:errors />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoEstadoA.etiqueta.nombre" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="nombre" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoEstadoA.nombre}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoEstadoA.etiqueta.descripcion" />
                </div>
                <div class="td" align="left">
                    <input type="text" 
                           name="descripcion" 
                           size="50" 
                           maxlength="100" 
                           value="${formaNuevoEstadoA.descripcion}" />
                </div>
            </div>
            <div class="tr">
                <div class="td" align="right">
                    <fmt:message key="formaNuevoEstadoA.etiqueta.pais" />
                </div>
                <div class="td" align="left">
                    <select name="idPais" >
                      <c:forEach var="pais" items="${formaNuevoEstadoA.paises}">
                        <option value='<c:out value="${pais.id}" />'><c:out value="${pais.nombre}" /></option>
                      </c:forEach>
                    </select>
                </div>
            </div>
                  
            <div class="tr">
                <div class="td" style="width:400px;" align="center">
                    <input type="submit" 
                           name="submit"
                           value="Agregar y terminar"/>
                    <input type="submit" 
                           name="submit"
                           value="Agregar y volver"
                           onclick="forma.action='procesarRegistroEstadoA.do?volver=si'"/>
                    <input type="button"
                           value="Reset"
                           onclick="location.href='solicitarRegistroEstadoA.do'" />
                    <input type="submit" 
                           name="org.apache.struts.taglib.html.CANCEL" 
                           value="cancelar" 
                           onclick="bCancel=true;">    
                </div>
            </div>
        </div>
    </form>