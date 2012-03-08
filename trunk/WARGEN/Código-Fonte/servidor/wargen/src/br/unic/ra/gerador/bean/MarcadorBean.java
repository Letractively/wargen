package br.unic.ra.gerador.bean;


public class MarcadorBean {
	
	private int id;
	private UsuarioBean usuario;
	private ArquivoBean arquivoMarcador;
	private ArquivoBean arquivoImprimir;
	
	public MarcadorBean() {
		
	}

	public MarcadorBean(int id, UsuarioBean usuario, ArquivoBean marcador) {
		this.id = id;
		this.usuario = usuario;
		this.arquivoMarcador = marcador;
	}

	public int getId() {
		return id;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public ArquivoBean getArquivoMarcador() {
		return arquivoMarcador;
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

	public void setArquivoMarcador(ArquivoBean marcador) {
		this.arquivoMarcador = marcador;
	}	

	public void setArquivoImprimir(ArquivoBean arquivoImprimir) {
		if (arquivoImprimir != null) {
			this.arquivoImprimir = arquivoImprimir;			
		}
	}	

}
