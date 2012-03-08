package br.unic.wargen.gerador.bean;

public class UsuarioBean {

	private int id;
	private String nome;
	private String login;
	private String senha;
	private boolean status;
	
	public UsuarioBean() {
		this.id = 0;
		this.nome = null;
		this.login = null;
		this.senha = null;
		this.status = false;
	}

	public UsuarioBean(int id, String nome, String login, String senha, boolean status) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public boolean getStatus() {
		return status;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
