package com.ipartek.formacion.tiendavirtual.accesodatos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.tiendavirtual.modelos.Producto;

public class ProductosDaoMySql implements Dao<Long, Producto> {
	private static final String PRODUCTOS_GET_ALL = "{ call productos_getAll() }";
	private static final String PRODUCTOS_SELECT_BY_ID = "{ call productos_getById(?) }";
	private static final String PRODUCTOS_INSERT = "{ call productos_insert(?,?,?,?,?) }";
	private static final String PRODUCTOS_UPDATE = "{ call productos_update(?,?,?,?,?) }";
	private static final String PRODUCTOS_DELETE = "{ call productos_delete(?,?,?,?) }";
	private static final String PRODUCTOS_DELETE_BY_ID = "{ call productos_deleteById(?) }";
	
	public String url, user, password, driver;
	
	private static ProductosDaoMySql instancia;
	
	public static ProductosDaoMySql crearInstancia(String driver, String url, String user, String password) {
		return instancia = new ProductosDaoMySql(driver, url, user, password);
	}
	
	public static ProductosDaoMySql getInstancia() {
		if(instancia == null) {
			throw new AccesoDatosException("Se debe crear la instancia con crearInstancia y los datos de configuración");
		}
		return instancia;
	}
	
	private ProductosDaoMySql(String driver, String url, String user, String password) {
		
		this.url = url;
		this.user = user;
		this.password = password;
		this.driver = driver;
	}

	private Connection getConnection() {
		
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al conectar a la base de datos", e);
		} catch (ClassNotFoundException e) {
			throw new AccesoDatosException("No se ha encontrado el driver de JDBC de MySQL", e);
		}
	}

	@Override
	public Iterable<Producto> getAll() {
		try (Connection con = getConnection()) {
			try (CallableStatement cs = con.prepareCall(PRODUCTOS_GET_ALL)) {
				ResultSet rs = cs.executeQuery();

				ArrayList<Producto> productos = new ArrayList<>();

				Producto producto;

				while (rs.next()) {
					producto = new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"),
							rs.getBigDecimal("precio"), rs.getInt("cantidad"));

					productos.add(producto);
				}

				return productos;

			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido llamar al procedimiento " + PRODUCTOS_GET_ALL);
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al cerrar la conexión a la base de datos", e);
		}
	}

	@Override
	public Producto getById(Long id) {
		try (Connection con = getConnection()) {
			
			try (CallableStatement cs = con.prepareCall(PRODUCTOS_SELECT_BY_ID)) {
				//System.out.println(id);
				cs.setLong(1, id);
				ResultSet rs = cs.executeQuery();
				Producto producto = null;
				while(rs.next()) {
					producto = new Producto(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"),
							rs.getBigDecimal("precio"), rs.getInt("cantidad"));
				}
				return producto;
			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido llamar al procedimiento " + PRODUCTOS_SELECT_BY_ID, e);
			
			}
		}catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al cerrar la conexión a la base de datos", e);
		}
		
		
		//throw new UnsupportedOperationException("Método no implementado");
	}

	@Override
	public Producto insert(Producto producto) {
		try (Connection con = getConnection()) {
			try (CallableStatement cs = con.prepareCall(PRODUCTOS_INSERT)) {
				cs.setString(1, producto.getNombre());
				cs.setString(2, producto.getDescripcion());
				cs.setBigDecimal(3, producto.getPrecio());
				cs.setInt(4, producto.getCantidad());
				cs.registerOutParameter(5, java.sql.Types.INTEGER);
				
				cs.executeUpdate();
				
				producto.setId(cs.getLong(5));
				
				return producto;

			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido llamar al procedimiento " + PRODUCTOS_GET_ALL);
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al cerrar la conexión a la base de datos", e);
		}
	}

	@Override
	public Producto update(Producto objeto) {
		try (Connection con = getConnection()) {
			try (CallableStatement cs = con.prepareCall(PRODUCTOS_UPDATE)) {
				cs.setLong(1, objeto.getId());
				cs.setString(2, objeto.getNombre());
				cs.setString(3, objeto.getDescripcion());
				cs.setBigDecimal(4, objeto.getPrecio());
				cs.setInt(5, objeto.getCantidad());
				cs.executeUpdate();

			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido llamar al procedimiento " + PRODUCTOS_UPDATE);
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al cerrar la conexión a la base de datos", e);
		}
		//throw new UnsupportedOperationException("Método no implementado");
		return null;
	}

	@Override
	public Producto delete(Producto objeto) {
		try (Connection con = getConnection()) {
			try (CallableStatement cs = con.prepareCall(PRODUCTOS_DELETE)) {
				cs.setLong(1, objeto.getId());
				cs.setString(2, objeto.getNombre());
				cs.setString(3, objeto.getDescripcion());
				cs.setBigDecimal(4, objeto.getPrecio());
				cs.executeUpdate();

			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido llamar al procedimiento " + PRODUCTOS_DELETE);
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al cerrar la conexión a la base de datos", e);
		}
		//throw new UnsupportedOperationException("Método no implementado");
		return null;
	}

	@Override
	public Producto deleteById(Long id) {
		try (Connection con = getConnection()) {
			try (CallableStatement cs = con.prepareCall(PRODUCTOS_DELETE_BY_ID)) {
				cs.setLong(1, id);
				cs.executeUpdate();
				return null;

			} catch (SQLException e) {
				throw new AccesoDatosException("No se ha podido llamar al procedimiento " + PRODUCTOS_DELETE_BY_ID);
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Ha habido un error al cerrar la conexión a la base de datos", e);
		}
	}

}
