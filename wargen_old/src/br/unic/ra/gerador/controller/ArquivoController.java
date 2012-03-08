package br.unic.ra.gerador.controller;

import java.io.IOException;

import br.unic.ra.gerador.bean.ArquivoBean;
import br.unic.ra.gerador.dao.ArquivoDAO;

public class ArquivoController {
	
	public boolean check(String nome, String extensao) throws Exception {
		return new ArquivoDAO().check(nome, extensao);
	}
	
	public void deleteByNomeExtensao(String nome, String extensao) throws IOException, Exception {
		new ArquivoDAO().deleteDeleteByNomeExtensao(nome, extensao);
	}
	
	public ArquivoBean loadById(int id) throws Exception {
		
		if (id < 1) {
			throw new Exception("Parâmetro 'id' inválido ao carregar arquivo.");
		}
		
		return new ArquivoDAO().loadById(id);
	}
	
	public ArquivoBean loadByNomeExtensao(String nome, String extensao) throws Exception {
		return new ArquivoDAO().loadByNomeExtensao(nome, extensao);
	}

}
