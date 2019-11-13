package com.ipartek.formacion.tiendavirtual.webapp.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tiendavirtual.servicios.ProductoServicio;

/**
 * Servlet implementation class EliminarProductoServlet
 */
@WebServlet("/eliminar")
public class EliminarProductoServlet extends HttpServlet {
	private static final String PRODUCTOS_JSP = "/WEB-INF/vistas/productos.jsp";
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos");
		try {
			//System.out.println(Long.parseLong(request.getParameter("id")));
		servicio.delete(Long.parseLong(request.getParameter("id")));}
		catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("productos", servicio.getAll());
		request.getRequestDispatcher(PRODUCTOS_JSP).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
