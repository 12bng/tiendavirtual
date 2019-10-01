package com.ipartek.formacion.tiendavirtual.webapp.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tiendavirtual.modelos.Mensaje;
import com.ipartek.formacion.tiendavirtual.modelos.Producto;
import com.ipartek.formacion.tiendavirtual.servicios.ProductoServicio;

/**
 * Servlet implementation class EditarProductoServlet
 */
@WebServlet("/editar")
public class EditarProductoServlet extends HttpServlet {
	private static final String EDITAR_PRODUCTOS_JSP = "/WEB-INF/vistas/editProducto.jsp";
	//private static final String PRODUCTOS_JSP = "/WEB-INF/vistas/productos.jsp";
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos");
		request.setAttribute("producto", servicio.getById(Long.parseLong(request.getParameter("id"))));
		request.setAttribute("id", request.getParameter("id"));
		request.getRequestDispatcher(EDITAR_PRODUCTOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos");
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		String precio = request.getParameter("precio");
		String cantidad = request.getParameter("cantidad");
		Producto producto = new Producto(Long.parseLong((String) request.getParameter("id")), nombre, descripcion, precio, cantidad);
		if (producto.isError()) {
			request.setAttribute("producto", producto);
		} else {
			//producto = ((ProductoServicio) getServletContext().getAttribute("servicioProductos")).insert(producto);
			servicio.update(producto);
			request.setAttribute("mensaje", new Mensaje("success", "Registro editado correctamente con el id " + producto.getId()));
			request.getRequestDispatcher("/productos").forward(request, response);
			
			return;
		}
		request.getRequestDispatcher(EDITAR_PRODUCTOS_JSP).forward(request, response);
		
	}

}
