package com.ipartek.formacion.tiendavirtual.accesodatos;

public interface Dao<PK, T> {
	Iterable<T> getAll();
	Iterable<T> getAllProductos();
	T getById(PK id);
	
	T insert(T objeto);
	T update(T objeto);
	T delete(PK id);
	String login(String correo, String consrasena);
}
