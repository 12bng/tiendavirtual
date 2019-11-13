<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>Imagen</th>
			<th>Tienda</th>
			<th>Nombre</th>
			<th>Descripción</th>
			<th>Precio</th>
			<th>Cantidad</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${productos}" var="p">
			<tr>
				<th> <img src=${p.imagenurl} height="150" width="150"> </th>
				<th>${p.tienda}</th>
				<td>${p.nombre}</td>
				<td>${p.descripcion}</td>
				
				<td><fmt:formatNumber value="${p.precio}" type="currency" /></td>
				<c:if test="${p.cantidad < 6}">
				<td><p style="color:red">${p.cantidad}</p></td>
				</c:if>
				<c:if test="${p.cantidad > 5}">
				<td>${p.cantidad}</td>
				</c:if>
				
			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
