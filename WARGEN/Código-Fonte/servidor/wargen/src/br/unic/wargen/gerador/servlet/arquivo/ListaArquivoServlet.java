package br.unic.wargen.gerador.servlet.arquivo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unic.wargen.gerador.bean.UsuarioBean;
import br.unic.wargen.gerador.controller.ArquivoController;
import br.unic.wargen.gerador.controller.MarcadorController;
import br.unic.wargen.gerador.controller.ModeloController;
import br.unic.wargen.gerador.manager.SessionManager;
import br.unic.wargen.gerador.security.Autenticacao;
import br.unic.wargen.gerador.utils.ui.UtilitariosUI;

public class ListaArquivoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if (Autenticacao.verificarUsuarioAutenticado(request, response)) {
				UsuarioBean usuario = SessionManager.getUsuarioAutenticado(request.getSession());
				
				request.setAttribute("listaModelos", ModeloController.listarModelosPorUsuario(usuario.getId()));
				request.setAttribute("listaMarcadores", MarcadorController.listarMarcadoresPorUsuario(usuario.getId()));
				request.setAttribute("listaArquivos", ArquivoController.listarArquivosPorUsuario(usuario.getId()));
				
				getServletContext().getRequestDispatcher("/arquivo/ListaArquivo.jsp").forward(request, response);
			}			
		} catch (Exception exc) {
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), request);
			getServletContext().getRequestDispatcher("/arquivo/ListaArquivo.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			if (Autenticacao.verificarUsuarioAutenticado(request, response)) {
				UsuarioBean usuario = SessionManager.getUsuarioAutenticado(request.getSession());
				getServletContext().getRequestDispatcher("/ra/usuarios/" + usuario.getLogin() + "/" + request.getParameter("caminho")).forward(request, response);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
