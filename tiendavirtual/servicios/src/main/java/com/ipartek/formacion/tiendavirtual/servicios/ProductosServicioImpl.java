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
	public Producto delete(Long id) {
		try {
			return dao.delete(id);
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
	public Iterable<Producto> getAllProductos() {
		try {
			return dao.getAllProductos();
		} catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al pedir el listado", e);
		}
	}

	@Override
	public Producto getById(long parseLong) {
		try {
			return dao.getById(parseLong);
		} catch (AccesoDatosException e) {
			throw new ServiciosException("Ha habido un error al pedir el listado", e);
		}
	}


}
