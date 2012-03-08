package br.unic.wargen.gerador.manager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.unic.wargen.gerador.bean.AssociacaoBean;

public class FileManager {
	
	public static void criarArquivoParametros(AssociacaoBean associacao) throws IOException {
		
		File arquivo = new File(associacao.getCaminhoArquivoParametros());

		arquivo.mkdirs();
		
		if (arquivo.isFile()) {
			if (arquivo.exists()) {
				arquivo.delete();
			}
		}
		
		FileWriter fw = new FileWriter(arquivo + "\\parametros.xml");
		fw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		fw.append("<root>");
		fw.append("<usuario login=\"" + associacao.getModelo().getUsuario().getLogin() + "\" />");
		fw.append("<modelo nome=\""+ associacao.getModelo().getArquivoModelo().getNome() + "\" extensao=\"" + associacao.getModelo().getArquivoModelo().getExtensao() + "\" " +						  
						  "caminho=\"/wargen/ra/usuarios/" + associacao.getModelo().getUsuario().getLogin() + "/modelos/" + associacao.getModelo().getArquivoModelo().getNome() + "." + associacao.getModelo().getArquivoModelo().getExtensao() + "\" " +  
						  "tipo=\"" + associacao.getModelo().getArquivoModelo().getExtensao() +
						  "\" />");
		fw.append("<marcador nome=\""+ associacao.getMarcador().getArquivoMarcador().getNome() + "\" extensao=\"" + associacao.getMarcador().getArquivoMarcador().getExtensao() + "\"" + 
						  "caminho=\"/wargen/ra/usuarios/" + associacao.getMarcador().getUsuario().getLogin() + "/marcadores/" + associacao.getMarcador().getArquivoMarcador().getNome() + "." + associacao.getMarcador().getArquivoMarcador().getExtensao() +
						  "\" />");
		fw.append("<interacao isRotacao=\"" + associacao.isRotacao() + "\" " +
							 "isMovimento=\"" + associacao.isMovimento() + "\" "+
							 "isEscala=\"" + associacao.isEscala() + "\" />");
		fw.append("</root>");
		fw.close();
		
	}
	
	public static void excluirArquivo(String arquivo) throws Exception {
		
		if (arquivo != null && arquivo.trim() != "") {
			
			File arquivoDisco = new File(arquivo);
			
			if (arquivoDisco != null && arquivoDisco.exists()) {
				
				arquivoDisco.delete();				
			}
			
			if (arquivoDisco.exists()) {
				
				throw new Exception("Erro ao remover arquivo do disco.");
			}			
		}
	}
		
}
