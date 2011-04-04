package br.unic.ra.gerador.bean;

import br.unic.ra.gerador.core.bean.UsuarioBean;

public class ObjetoBean {

	private int id;
	private UsuarioBean usuario;
	private ModeloBean modelo;
	private MarcadorBean marcador;
	private boolean isPublico;
	private String descricao;
	
	public ObjetoBean() {
		
	}

	public ObjetoBean(UsuarioBean usuario, ModeloBean modelo,
			MarcadorBean marcador, boolean isPublico) {
		this.usuario = usuario;
		this.modelo = modelo;
		this.marcador = marcador;
		this.isPublico = isPublico;
	}

	public int getId() {
		return id;
	}
	
	public UsuarioBean getUsuario() {
		return usuario;
	}

	public ModeloBean getModelo() {
		return modelo;
	}

	public MarcadorBean getMarcador() {
		return marcador;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public boolean isPublico() {
		return isPublico;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void setModelo(ModeloBean modelo) {
		this.modelo = modelo;
	}

	public void setMarcador(MarcadorBean marcador) {
		this.marcador = marcador;
	}

	public void setPublico(boolean isPublico) {
		this.isPublico = isPublico;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
