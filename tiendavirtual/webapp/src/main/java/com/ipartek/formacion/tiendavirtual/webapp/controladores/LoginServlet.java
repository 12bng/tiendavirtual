package com.ipartek.formacion.tiendavirtual.webapp.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tiendavirtual.servicios.ProductoServicio;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("correo", "");
		request.setAttribute("error", "");
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos"); 
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		String nombre = servicio.login(correo, password);
		if(nombre==null) {
			request.setAttribute("correo", correo);
			request.setAttribute("error", "El correo o contraseña no es correcto");
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
		else {
			request.getSession().setAttribute("userName", nombre);
			request.getSession().setAttribute("userEmail", correo);
			if (nombre.equals("admin")) {
				request.getRequestDispatcher("/productos").forward(request, response);
			}
			else request.getRequestDispatcher("/compras").forward(request, response);
		}
		
		
	}

}
