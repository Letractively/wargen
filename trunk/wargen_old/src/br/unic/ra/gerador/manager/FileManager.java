package br.unic.ra.gerador.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.unic.ra.gerador.bean.MarcadorBean;
import br.unic.ra.gerador.bean.ModeloBean;

public class FileManager {

	public static final String CAMINHO_ARQUIVOS = "ra/arquivos/";
	public static final String CAMINHO_MODELOS = "ra/modelos/";
	public static final String CAMINHO_MARCADORES = "ra/marcadores/";
	
	public static void criarArquivoParametros(String destino, ModeloBean modelo, MarcadorBean marcador) throws IOException {
		File arquivo = new File(destino);

		arquivo.mkdirs();
		
		if (arquivo.isFile()) {
			if (arquivo.exists()) {
				arquivo.delete();
			}
		}
		
		FileWriter fw = new FileWriter(arquivo + "/parametros.xml");
		fw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		fw.append("<root>");
		fw.append("<usuario login=\"" + modelo.getUsuario().getLogin() + "\" />");
		fw.append("<objeto>");
		fw.append("<modelo nome=\""+ modelo.getModelo().getFullName() + "\"/>");
		fw.append("<marcador nome=\"" + marcador.getMarcador().getFullName() + "\"/>");
		fw.append("</objeto>");
		fw.append("</root>");
		fw.close();
		
	}
		
}
