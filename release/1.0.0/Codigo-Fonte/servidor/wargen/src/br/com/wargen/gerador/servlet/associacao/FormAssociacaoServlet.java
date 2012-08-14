package br.com.wargen.gerador.servlet.associacao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.wargen.gerador.bean.AssociacaoBean;
import br.com.wargen.gerador.bean.UsuarioBean;
import br.com.wargen.gerador.controller.AssociacaoController;
import br.com.wargen.gerador.controller.MarcadorController;
import br.com.wargen.gerador.controller.ModeloController;
import br.com.wargen.gerador.manager.SessionManager;
import br.com.wargen.gerador.security.Autenticacao;
import br.com.wargen.gerador.utils.ui.UtilitariosUI;

public class FormAssociacaoServlet extends HttpServlet {

	private static final long serialVersionUID = -8998837194374015469L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Autenticacao.verificarUsuarioAutenticado(request, response);
			
			UsuarioBean usuario = SessionManager.getUsuarioAutenticado(request.getSession());
			
			request.setAttribute("listaModelos", ModeloController.listarModelosPorUsuario(usuario.getId()));
			request.setAttribute("listaMarcadores", MarcadorController.listarMarcadoresPorUsuario(usuario.getId()));
			
			getServletContext().getRequestDispatcher("/associacao/FormAssociacao.jsp").forward(request, response);
		} catch (Exception exc) {
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), request);
			getServletContext().getRequestDispatcher("/associacao/FormAssociacao.jsp").forward(request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioBean usuario = null;
		AssociacaoBean associacao = null;
		
		try {
			usuario = SessionManager.getUsuarioAutenticado(request.getSession());
			
			request.setAttribute("descricao", request.getParameter("descricao"));
			request.setAttribute("modelo", request.getParameter("modelo"));
			request.setAttribute("marcador", request.getParameter("marcador"));
			request.setAttribute("isPublico", request.getParameter("isPublico"));
			request.setAttribute("isMovimento", request.getParameter("isMovimento"));
			request.setAttribute("isRotacao", request.getParameter("isRotacao"));
			request.setAttribute("isEscala", request.getParameter("isEscala"));
			request.setAttribute("listaModelos", ModeloController.listarModelosPorUsuario(usuario.getId()));
			request.setAttribute("listaMarcadores", MarcadorController.listarMarcadoresPorUsuario(usuario.getId()));
			
			validarCampos(request);
			
			associacao = new AssociacaoBean();
			associacao.setUsuario(usuario);
			associacao.setDescricao(request.getAttribute("descricao").toString());
			associacao.setModelo(
					ModeloController.carregarModeloPorId(
							Integer.parseInt(request.getParameter("modelo").toString())));
			associacao.setMarcador(
					MarcadorController.carregarMarcadorPorId(
							Integer.parseInt(request.getParameter("marcador").toString())));

			if(request.getParameter("isPublico") != null) {
				associacao.setPublico(true);
			}
			
			if(request.getParameter("isMovimento") != null) {
				associacao.setMovimento(true);
			}
			
			if(request.getParameter("isRotacao") != null) {
				associacao.setRotacao(true);
			}
			
			if(request.getParameter("isEscala") != null) {
				associacao.setEscala(true);
			}			
			
			associacao.setCaminhoArquivoParametros(
					request.getSession().getServletContext().getRealPath("\\ra\\usuarios\\" + usuario.getLogin()));
			
			AssociacaoController.inserirAssociacao(associacao);
			
			UtilitariosUI.mostrarMensagem("Associação realizada com sucesso!", request);
			getServletContext().getRequestDispatcher("/associacao/FormAssociacao.jsp").forward(request, response);
		} catch (Exception exc) {
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), request);
			getServletContext().getRequestDispatcher("/associacao/FormAssociacao.jsp").forward(request, response);
		}
	}
	
	private void validarCampos(HttpServletRequest request) throws Exception {
		
		if (request.getParameter("descricao").trim().equals("")) {
			throw new Exception("Por favor, preencha o campo descrição.");
		}
		
		if (request.getParameter("modelo") == null ||
			request.getParameter("modelo").trim().equals("")) {
			throw new Exception("Por favor, selecione um modelo.");
		}
		
		if (request.getParameter("marcador") == null ||
			request.getParameter("marcador").trim().equals("")) {
			throw new Exception("Por favor, selecione um marcador.");
		}
		
	}
	
}
