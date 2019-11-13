package com.ipartek.formacion.tiendavirtual.webapp.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.tiendavirtual.modelos.Mensaje;
import com.ipartek.formacion.tiendavirtual.servicios.ProductoServicio;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("correo", "");
		request.getSession().setAttribute("admin", null);
		request.setAttribute("error", "");
		request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductoServicio servicio = (ProductoServicio) getServletContext().getAttribute("servicioProductos"); 
		String usuario = request.getParameter("correo");
		String password = request.getParameter("password");
		String roll = servicio.login(usuario, password);
		if(roll==null) {
			request.setAttribute("correo", usuario);
			request.setAttribute("mensaje", new Mensaje("warning", "usuario o contraseña incorrectos"));
			//request.setAttribute("error", "El correo o contraseña no es correcto");
			request.getRequestDispatcher("/WEB-INF/vistas/login.jsp").forward(request, response);
		}
		else {
			if(Integer.parseInt(roll)==1) {
				request.getSession().setAttribute("admin", roll);
				request.getSession().setAttribute("userName", usuario + " - Administrador");
			}
			else {
				request.getSession().setAttribute("userName", usuario + " - Usuario");
			}
			//request.getSession().setAttribute("userEmail", usuario);
			//if (nombre.equals("admin")) {
			//	request.getRequestDispatcher("/productos").forward(request, response);
			//}
			//else request.getRequestDispatcher("/compras").forward(request, response);
			request.getRequestDispatcher("/compras").forward(request, response);
		}
		
		
	}

}
