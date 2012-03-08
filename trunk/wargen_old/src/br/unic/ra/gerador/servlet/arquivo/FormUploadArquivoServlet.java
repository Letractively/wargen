package br.unic.ra.gerador.servlet.arquivo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unic.ra.gerador.bean.ArquivoBean;
import br.unic.ra.gerador.bean.MarcadorBean;
import br.unic.ra.gerador.bean.ModeloBean;
import br.unic.ra.gerador.controller.MarcadorController;
import br.unic.ra.gerador.controller.ModeloController;
import br.unic.ra.gerador.core.bean.UsuarioBean;
import br.unic.ra.gerador.factory.ConnectionFactory;
import br.unic.ra.gerador.manager.DBManager;
import br.unic.ra.gerador.utils.ui.UtilitariosUI;

public class FormUploadArquivoServlet extends HttpServlet {

	private UsuarioBean usuario;
	private ModeloBean modelo;
	private MarcadorBean marcador;
	private ArquivoBean arquivo;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FileOutputStream fileOut = null;
		
		try {
		
			usuario = (UsuarioBean) request.getSession().getAttribute("usuario");
			
			// to get the content type information from JSP Request Header
			String contentType = request.getContentType();
			
			// here we are checking the content type is not equal to Null and
			// as well as the passed data from mulitpart/form-data is greater than or
			// equal to 0
			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
				DataInputStream in = new DataInputStream(request.getInputStream());
				
				// we are taking the length of Content type data
				int formDataLength = request.getContentLength();
				byte dataBytes[] = new byte[formDataLength];
				int byteRead = 0;
				int totalBytesRead = 0;
				
				// this loop converting the uploaded file into byte code
				while (totalBytesRead < formDataLength) {
					byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
					totalBytesRead += byteRead;
				}
				String file = new String(dataBytes);
				
				// for saving the file name
				String saveFile = file.substring(file.indexOf("filename=\"") + 10);
				saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
				saveFile = saveFile.substring(saveFile.lastIndexOf("\\")
				 + 1,saveFile.indexOf("\""));
				int lastIndex = contentType.lastIndexOf("=");
				String boundary = contentType.substring(lastIndex + 1, contentType.length());
				int pos;
				
				// extracting the index of file 
				pos = file.indexOf("filename=\"");
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				pos = file.indexOf("\n", pos) + 1;
				int boundaryLocation = file.indexOf(boundary, pos) - 4;
				int startPos = ((file.substring(0, pos)).getBytes()).length;
				int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
				
				//NOME - EXTENSÃO ARQUIVO
								
				String nome = saveFile.substring(0, saveFile.length() - 4);
				String extensao = saveFile.substring(saveFile.length() - 3, saveFile.length());
				
				String caminho = request.getSession().getServletContext().getRealPath("\\wargen");
				
				if(extensao.equals("dae") || extensao.equals("kmz")) {
					caminho += "\\modelos\\" + usuario.getLogin() +  "\\";
				}
				else if (extensao.equals("pat")) {
					caminho += "\\marcadores\\" + usuario.getLogin() +  "\\";
				}

				new File(caminho).mkdirs();
				
				saveFile = caminho + saveFile;
				
				if (new File(saveFile).exists()) {
					throw new Exception("Arquivo já existe.");
				}
				
				// creating a new file with the same name and writing the 
				// content in new file
				fileOut = new FileOutputStream(saveFile);
				fileOut.write(dataBytes, startPos, (endPos - startPos));
				fileOut.flush();
				fileOut.close();
				
				//------------------------------------------------------				
				arquivo = new ArquivoBean();
				arquivo.setNome(nome);
				arquivo.setExtensao(extensao);
				arquivo.setNomeApresentacao(nome);
				
				if(extensao.equals("dae") || extensao.equals("kmz")) {
					modelo = new ModeloBean();
					modelo.setModelo(arquivo);
					modelo.setUsuario(usuario);
					new ModeloController().insert(modelo);
				}
				else if (extensao.equals("pat")) {
					marcador = new MarcadorBean();
					marcador.setUsuario(usuario);
					marcador.setMarcador(arquivo);
					new MarcadorController().insert(marcador);
				}
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), "/wargen/arquivo/FormArquivo", request, response);
		}
		finally {
			if (fileOut != null) {
				fileOut.flush();
				fileOut.close();
			}
		}
	}
}
