package br.com.wargen.gerador.bean;


public class ModeloBean {
	
	private int id;
	private UsuarioBean usuario;
	private ArquivoBean arquivoModelo;
	private ArquivoBean imagem;
	
	public ModeloBean() {
		
	}

	public ModeloBean(int id, ArquivoBean modelo, ArquivoBean imagem) {
		this.id = id;
		this.arquivoModelo = modelo;
		this.imagem = imagem;
	}

	public int getId() {
		return id;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public ArquivoBean getArquivoModelo() {
		return arquivoModelo;
	}

	public ArquivoBean getImagem() {
		return imagem;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void setArquivoModelo(ArquivoBean modelo) {
		this.arquivoModelo = modelo;		
	}

	public void setImagem(ArquivoBean imagem) {
		if (imagem != null) {
			this.imagem = imagem;
		}
	}

}
