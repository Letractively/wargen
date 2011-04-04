package br.unic.ra.gerador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AplicativoServlet extends HttpServlet {

	private static final long serialVersionUID = 1338418871747250249L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.getSession().setAttribute("urlParametros",
				"aplicativos/" + request.getParameter("caminhoObjeto") + "/parametros.xml");
			
			response.sendRedirect("ra/aplicativo.jsp");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
}
