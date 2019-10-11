package com.ipartek.formacion.tiendavirtual.webapp.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tiendavirtual.modelos.Mensaje;
import com.ipartek.formacion.tiendavirtual.servicios.ProductoServicio;

/**
 * Servlet implementation class CarritoServlet
 */
@WebServlet("/carrito")
public class CarritoServlet extends HttpServlet {
	private static final String CARRITO_JSP = "/WEB-INF/vistas/carrito.jsp";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos");
			
			request.setAttribute("productos", servicio.getAllCarrito((String) request.getSession().getAttribute("userEmail")));
			
			//request.setAttribute("mensaje",	new Mensaje("info", "Se ha cargado la lista de productos"));
		} catch (Exception e) {
			e.printStackTrace();
			
			request.setAttribute("mensaje", new Mensaje("danger", "Ha habido un error al pedir los datos al servidor"));
		}
		
		request.getRequestDispatcher(CARRITO_JSP).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("producto"));
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos"); 
//		System.out.println(Integer.parseInt(request.getParameter("producto")));
//		System.out.println((String) request.getSession().getAttribute("userEmail"));
		servicio.alCarrito(Integer.parseInt(request.getParameter("producto")),(String) request.getSession().getAttribute("userEmail"));
		
		request.getRequestDispatcher("/compras").forward(request, response);
	}

}
