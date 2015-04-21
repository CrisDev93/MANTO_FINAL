    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>
    <%@ taglib uri='http://java.sun.com/jstl/fmt' prefix='fmt' %>
    <%@ taglib uri='/WEB-INF/vista/etiquetas/struts-html.tld' prefix='html' %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
		
      <c:forEach var='estadoa' items='${formaNuevoAutoEstadosA.estadosa}'>
        <li onclick="seleccionarEstadoa(<c:out value='${estadoa.id}' />, &quot;<c:out value='${estadoa.nombre}' />&quot;, &quot;<c:forEach var='pais' items='${formaNuevoAutoEstadosA.paises}'><c:if test='${pais.id == estadoa.idPais}'><c:out value='${pais.nombre}'/></c:if></c:forEach>&quot;)"><c:out value='${estadoa.nombre}' /></li>
      </c:forEach>