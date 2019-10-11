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
 * Servlet implementation class NuevoUsuarioServlet
 */
@WebServlet("/nuevoUsuario")
public class NuevoUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuevoUsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/vistas/crearNuevoUsuario.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos");
		String correo=request.getParameter("correo");
		String usuario=request.getParameter("nombre");
		String contraseña=request.getParameter("password");
		if(servicio.nuevoUsuario(correo, usuario, contraseña)==null) {
			request.setAttribute("mensaje", new Mensaje("succes", "Usuario creado correctamente"));
			request.getRequestDispatcher("/compras").forward(request, response);
		}
		else{
			request.setAttribute("mensaje", new Mensaje("warning", "Ya existe un usuario con esa direccion de correo"));
			request.getRequestDispatcher("/WEB-INF/vistas/crearNuevoUsuario.jsp").forward(request, response);
		};
	}

}
