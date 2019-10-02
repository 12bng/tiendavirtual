<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<link rel="stylesheet" type="text/css" href="/WEB-INF/css/tiendavirtual.css"/>
<%@ include file="/WEB-INF/vistas/includes/cabecera.jsp"%>
<form method="post">
	<div class="form-group row">
		<label for="correo" class="col-sm-2 col-form-label">Correo</label>
		<div class="col-sm-10">
			<input type="email"
				id="correo"
				name="correo" placeholder="Correo"
				value="<%=request.getAttribute("correo")%>">
		</div>
	</div>
	<div class="form-group row">
		<label for="password" class="col-sm-2 col-form-label">Contraseña</label>
		<div class="col-sm-10">
			<input type="password"
				id="password"
				name="password" placeholder="Contraseña"
				value="">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">Entrar</button>
		</div>
	</div>
	<p id="error" style="color:#FF0000"><%=request.getAttribute("error") %></p>
</form>

<form action="/webapp/nuevoUsuario" method="get">
<div class="form-group row">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">Nuevo usuario</button>
		</div>
	</div>
</form>

<%@ include file="/WEB-INF/vistas/includes/pie.jsp"%>
