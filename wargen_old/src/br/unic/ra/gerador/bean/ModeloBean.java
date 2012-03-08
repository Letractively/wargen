package br.unic.ra.gerador.bean;

import br.unic.ra.gerador.core.bean.UsuarioBean;
import br.unic.ra.gerador.manager.FileManager;

public class ModeloBean {
	
	private int id;
	private UsuarioBean usuario;
	private ArquivoBean modelo;
	private ArquivoBean imagem;
	
	public ModeloBean() {
		
	}

	public ModeloBean(int id, ArquivoBean modelo, ArquivoBean imagem) {
		this.id = id;
		this.modelo = modelo;
		this.imagem = imagem;
	}

	public int getId() {
		return id;
	}

	public UsuarioBean getUsuario() {
		return usuario;
	}

	public ArquivoBean getModelo() {
		return modelo;
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

	public void setModelo(ArquivoBean modelo) {
		modelo.setCaminho(FileManager.CAMINHO_MODELOS + 
				modelo.getNome() + "." + modelo.getExtensao());
		this.modelo = modelo;		
	}

	public void setImagem(ArquivoBean imagem) {
		this.imagem = imagem;
	}

}
