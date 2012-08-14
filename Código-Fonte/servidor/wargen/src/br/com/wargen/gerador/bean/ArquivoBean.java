package br.com.wargen.gerador.bean;


public class ArquivoBean{
	
	private int id;
	private UsuarioBean usuario;
	private String nome;
	private String extensao;
	private String caminho;

	public ArquivoBean() {
		
	}

	public ArquivoBean(int id, UsuarioBean usuario, String nome, String caminho, String extensao) {
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.extensao = extensao;
		this.caminho = caminho;
	}

	public int getId() {
		return id;
	}
	
	public UsuarioBean getUsuario() {
		return usuario;
	}

	public String getNome() {
		return nome;
	}

	public String getExtensao() {
		return extensao;
	}
	
	public String getCaminho() {		
		return this.caminho;
	}
	
	public String getFullName() {
		return (this.nome + "." + this.extensao);
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setUsuario(UsuarioBean usuario) {
		this.usuario = usuario;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

}
