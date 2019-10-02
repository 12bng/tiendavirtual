<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>

<table class="table">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Descripción</th>
			<th>Precio</th>
			<th>Cantidad</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${productos}" var="p">
			<tr>
				<th>${p.id}</th>
				<td>${p.nombre}</td>
				<td>${p.descripcion}</td>
				<td><fmt:formatNumber value="${p.precio}" type="currency" /></td>
				<td>${p.cantidad}</td>
				<c:if test="${p.cantidad > 0}">
					<td>
						<form action="/webapp/carrito" method="post">
							<div class="form-group row">
								<div class="col-sm-10">
									<button id="producto" name="producto" value="${p.id}"
										type="submit" class="btn btn-primary">Añadir</button>
								</div>
							</div>
						</form>
					</td>
				</c:if>


			</tr>
		</c:forEach>
	</tbody>
</table>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
