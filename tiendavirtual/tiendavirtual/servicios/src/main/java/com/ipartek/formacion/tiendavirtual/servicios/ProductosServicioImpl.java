package com.ipartek.formacion.tiendavirtual.servicios;

import com.ipartek.formacion.tiendavirtual.accesodatos.AccesoDatosException;
import com.ipartek.formacion.tiendavirtual.accesodatos.Dao;
import com.ipartek.formacion.tiendavirtual.accesodatos.FabricaDao;
import com.ipartek.formacion.tiendavirtual.modelos.Producto;

public class ProductosServicioImpl implements ProductoServicio {

	private String configuracion;
	private FabricaDao fabrica;
	private Dao<Long, Producto> dao;
	
	public String getConfiguracion() {
		return configuracion;
	}

	private static ProductosServicioImpl instancia = null;
	
	public static ProductosServicioImpl getInstancia() {
		if(instancia == null) {
			throw new AccesoDatosException("Debes crear una instancia con la configuración con el método crearInstancia");
		}
		return instancia;
	}
	
	public static ProductosServicioImpl crearInstancia(String configuracion) {
		return instancia = new ProductosServicioImpl(configuracion);
	}
	
	private ProductosServicioImpl(String configuracion) {
		try {
			this.configuracion = configuracion;
			this.fabrica = FabricaDao.crearInstancia(configuracion);
			this.dao = fabrica.getDaoProducto();
		} catch (AccesoDatosException e) {
			throw new ServiciosException("Error al crear el dao", e);
		}
	}
	
	@Override
	public Iterable<Producto> getAll() {
		try {
			return dao.getAll();
		} catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al pedir el listado", e);
		}
	}

	@Override
	public Producto getById(Long id) {
		try {
		return dao.getById(id);
		} catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al buscar el prodructo mediante la ID", e);
		}
		
		//throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public Producto insert(Producto producto) {
		try {
			return dao.insert(producto);
			} catch (AccesoDatosException e) {
				throw new ServiciosException("Ha habido un error al añadir el prodructo", e);
			}
	}

	@Override
	public Producto update(Producto producto) {

		try {
			return dao.update(producto);
			} catch (AccesoDatosException e) {
				throw new ServiciosException("Ha habido un error al actualizar el prodructo", e);
			}
	}

	@Override
	public Producto delete(Producto producto) {
		try {
			return dao.delete(producto);
			} catch (AccesoDatosException e) {
				throw new ServiciosException("Ha habido un error al eliminar el prodructo", e);
			}
	}

	@Override
	public String login(String correo, String contrasena) {
		try {
			return dao.login(correo, contrasena);
			} catch (AccesoDatosException e) {
				throw new ServiciosException("Ha habido un error al logearse", e);
			}
	}

	@Override
	public Boolean alCarrito(Integer productoID, String correoUsuario) {
		try {
			return dao.alCarrito(productoID, correoUsuario);
		}catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al añadir un producto al carrito de la compra", e);
		}
	}

	@Override
	public String nuevoUsuario(String correo, String usuario, String contraseña) {
		try {
			return dao.nuevoUsuario(correo, usuario, contraseña);
		}catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al crear un nuevo usuario", e);
		}
	}

	@Override
	public Iterable<Producto> getAllCarrito(String correoUsuario) {
		try {
			return dao.getAllCarrito(correoUsuario);
		} catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al pedir el listado del carrito", e);
		}
	}

}
