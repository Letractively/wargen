package br.unic.ra.gerador.bean;

public class ArquivoBean{
	
	private int id;
	private String nome;
	private String extensao;
	private String caminho;
	private String nomeApresentacao;

	public ArquivoBean() {
		
	}

	public ArquivoBean(int id, String nome, String caminho, String extensao, String nomeApresentacao) {
		this.id = id;
		this.nome = nome;
		this.extensao = extensao;
		this.caminho = caminho;
		this.nomeApresentacao = nomeApresentacao;
	}

	public int getId() {
		return id;
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
	
	public String getNomeApresentacao() {
		return nomeApresentacao;
	}
	
	public void setId(int id) {
		this.id = id;
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
	
	public void setNomeApresentacao(String nomeApresentacao) {
		this.nomeApresentacao = nomeApresentacao;
	}

}
