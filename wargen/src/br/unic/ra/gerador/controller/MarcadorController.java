package br.unic.ra.gerador.controller;

import java.util.ArrayList;

import br.unic.ra.gerador.bean.MarcadorBean;
import br.unic.ra.gerador.dao.MarcadorDAO;

public class MarcadorController {
	
	public void insert(MarcadorBean marcador) throws Exception {
		new MarcadorDAO().insert(marcador);
	}

	public MarcadorBean loadById(int id) throws Exception {			
		if (id < 1) {
			throw new Exception("Código inválido");
		}
		
		return new MarcadorDAO().loadById(id);
	}
	
	public ArrayList<MarcadorBean> loadByUsuario(int usuarioId) throws Exception {			
			return new MarcadorDAO().loadByUsuario(usuarioId);
	}

}
