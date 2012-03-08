package br.unic.ra.gerador.servlet.arquivo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import br.unic.ra.gerador.utils.ui.UtilitariosUI;

public class FormArquivoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/arquivo/FormArquivo.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			validarCampos(request);
			
			request.getRequestDispatcher("/arquivo/FormArquivo.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			UtilitariosUI.mostrarMensagem(e.getMessage(), "/arquivo/FormArquivo.jsp", request, response);
		}
	}
	
	private void validarCampos(HttpServletRequest request) throws Exception {
		if (request.getParameter("nome_apresentacao").equals("")) {
			throw new Exception("Por favor, informe um nome de apresentação");
		}
		
		request.setAttribute("nome_apresentacao", request.getParameter("nome_apresentacao"));
		
		if (request.getParameter("arquivo").equals(null)) {
			throw new Exception("Por favor, informe um arquivo");
		}
		
		request.setAttribute("arquivo", request.getParameter("arquivo"));
	}
	
}
