package com.ipartek.formacion.tiendavirtual.servicios;

import com.ipartek.formacion.tiendavirtual.modelos.Producto;

public interface ProductoServicio {
	Iterable<Producto> getAll();
	Iterable<Producto> getAllProductos();
	
	Producto insert(Producto producto);
	Producto update(Producto producto);
	Producto delete(Long id);
	String login(String correo, String contrasena);
	Producto getById(long parseLong);
	
}
