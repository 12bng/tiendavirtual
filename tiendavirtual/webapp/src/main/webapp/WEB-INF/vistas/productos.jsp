<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>Imagen</th>
			<th>Id</th>
			<th>Nombre</th>
			<th>Descripción</th>
			<th>Precio</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${productos}" var="p">
			<tr>
				<th> <img src=${p.imagenurl} height="150" width="150"> </th>
				<th>${p.id}</th>
				<td>${p.nombre}</td>
				<td>${p.descripcion}</td>
				<td><fmt:formatNumber value="${p.precio}" type="currency" /></td>
				<td><a href="editar?id=${p.id}" class="btn btn-primary">Editar</a></td>
				<td><a href="eliminar?id=${p.id}" class="btn btn-primary">Eliminar</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="producto" class="btn btn-primary">Añadir producto</a>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
