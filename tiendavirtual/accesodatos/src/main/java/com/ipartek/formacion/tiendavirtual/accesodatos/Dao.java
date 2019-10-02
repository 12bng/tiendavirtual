package com.ipartek.formacion.tiendavirtual.accesodatos;

public interface Dao<PK, T> {
	Iterable<T> getAll();
	T getById(PK id);
	
	T insert(T objeto);
	T update(T objeto);
	T delete(T objeto);
	T deleteById(PK id);
	String login(String correo, String consrasena);
	Boolean alCarrito(Integer productoID, String correoUsuario);
}
