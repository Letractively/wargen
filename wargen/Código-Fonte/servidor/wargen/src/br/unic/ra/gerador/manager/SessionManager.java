package br.unic.ra.gerador.manager;

import javax.servlet.http.HttpSession;

import br.unic.ra.gerador.bean.UsuarioBean;

public class SessionManager {

	public static UsuarioBean getUsuarioAutenticado(HttpSession sessao) {
		return (UsuarioBean) sessao.getAttribute("usuarioAutenticado");
	}
	
	public static void setUsuarioAutenticado(HttpSession sessao, UsuarioBean usuario) {
		sessao.setAttribute("usuarioAutenticado", usuario);
	}
	
}
