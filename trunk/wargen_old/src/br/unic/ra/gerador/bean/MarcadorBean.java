package br.unic.ra.gerador.bean;

import br.unic.ra.gerador.core.bean.UsuarioBean;
import br.unic.ra.gerador.manager.FileManager;

public class MarcadorBean {
	
	private int id;
	private UsuarioBean usuario;
	private ArquivoBean marcador;
	private ArquivoBean arquivoImprimir;
	
	public MarcadorBean() {
		
	}

	public MarcadorBean(int id, ArquivoBean marcador) {
		this.id = id;
		this.marcador = marcador;
	}

	public int getId() {
		return id;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public ArquivoBean getMarcador() {
		return marcador;
	}

	public ArquivoBean getArquivoImprimir() {
		return arquivoImprimir;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void setMarcador(ArquivoBean marcador) {
		marcador.setCaminho(FileManager.CAMINHO_MARCADORES +
				marcador.getNome() + "." + marcador.getExtensao());
		this.marcador = marcador;
	}	

	public void setArquivoImprimir(ArquivoBean arquivoImprimir) {
		arquivoImprimir.setCaminho(FileManager.CAMINHO_ARQUIVOS + 
				arquivoImprimir.getNome() + "." + arquivoImprimir.getExtensao());
		this.arquivoImprimir = arquivoImprimir;
	}	

}
