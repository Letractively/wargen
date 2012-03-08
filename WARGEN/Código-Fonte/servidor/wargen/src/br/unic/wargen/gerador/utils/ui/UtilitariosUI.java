package br.unic.wargen.gerador.utils.ui;

import javax.servlet.http.HttpServletRequest;

public class UtilitariosUI {
	
	public static void mostrarMensagem(String mensagem, HttpServletRequest request) {
		request.getSession().setAttribute("mensagem", mensagem);
	}
}
