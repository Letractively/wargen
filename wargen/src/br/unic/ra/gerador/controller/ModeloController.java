package br.unic.ra.gerador.controller;

import java.util.ArrayList;

import br.unic.ra.gerador.bean.ModeloBean;
import br.unic.ra.gerador.dao.ModeloDAO;

public class ModeloController {
	
	public void insert(ModeloBean modelo) throws Exception {
		new ModeloDAO().insert(modelo);
	}

	public ModeloBean loadById(int id) throws Exception {			
		if (id < 1) {
			throw new Exception("Código inválido.");
		}
		
		return new ModeloDAO().loadById(id);
	}

	public ArrayList<ModeloBean> loadByUsuario(int usuarioId) throws Exception {
		return new ModeloDAO().loadByUsuario(usuarioId);
	}

}
