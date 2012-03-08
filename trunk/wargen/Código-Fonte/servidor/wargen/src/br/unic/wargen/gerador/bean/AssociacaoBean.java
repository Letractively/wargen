package br.unic.wargen.gerador.bean;


public class AssociacaoBean {

	private int id;
	private UsuarioBean usuario;
	private ModeloBean modelo;
	private MarcadorBean marcador;
	private boolean isPublico;
	private boolean isMovimento;
	private boolean isRotacao;
	private boolean isEscala;
	private String caminhoArquivoParametros;
	private String descricao;
	
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

	public boolean isRotacao() {
		return isRotacao;
	}

	public void setRotacao(boolean isRotacao) {
		this.isRotacao = isRotacao;
	}

	public boolean isMovimento() {
		return isMovimento;
	}

	public void setMovimento(boolean isMovimento) {
		this.isMovimento = isMovimento;
	}

	public boolean isEscala() {
		return isEscala;
	}
	
	public void setEscala(boolean isEscala) {
		this.isEscala = isEscala;
	}
	
	public String getCaminhoArquivoParametros() {
		return caminhoArquivoParametros;
	}

	public void setCaminhoArquivoParametros(String caminhoArquivoParametros) {
		this.caminhoArquivoParametros = caminhoArquivoParametros;
	}
	
}
