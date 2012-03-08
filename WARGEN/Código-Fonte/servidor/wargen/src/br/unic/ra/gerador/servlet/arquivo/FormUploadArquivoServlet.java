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
import br.unic.ra.gerador.bean.UsuarioBean;
import br.unic.ra.gerador.controller.ArquivoController;
import br.unic.ra.gerador.controller.MarcadorController;
import br.unic.ra.gerador.controller.ModeloController;
import br.unic.ra.gerador.manager.SessionManager;
import br.unic.ra.gerador.security.Autenticacao;
import br.unic.ra.gerador.utils.ui.UtilitariosUI;

public class FormUploadArquivoServlet extends HttpServlet {

	private static final long serialVersionUID = -4830012544368314150L;
	private UsuarioBean usuario;
	private ModeloBean modelo;
	private MarcadorBean marcador;
	private ArquivoBean arquivo;
	private String saveFile;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FileOutputStream fileOut = null;
		
		try {
			if (Autenticacao.verificarUsuarioAutenticado(request, response)) {				
		
				usuario = (UsuarioBean) SessionManager.getUsuarioAutenticado(request.getSession());
				
				String contentType = request.getContentType();
				
				if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
					DataInputStream in = new DataInputStream(request.getInputStream());
					
					int formDataLength = request.getContentLength();
					byte dataBytes[] = new byte[formDataLength];
					int byteRead = 0;
					int totalBytesRead = 0;
					
					while (totalBytesRead < formDataLength) {
						byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
						totalBytesRead += byteRead;
					}
					
					String file = new String(dataBytes);
					
					saveFile = file.substring(file.indexOf("filename=\"") + 10);
					saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
					saveFile = saveFile.substring(saveFile.lastIndexOf("\\")
					 + 1,saveFile.indexOf("\""));
					int lastIndex = contentType.lastIndexOf("=");
					String boundary = contentType.substring(lastIndex + 1, contentType.length());
					int pos;
					
					pos = file.indexOf("filename=\"");
					pos = file.indexOf("\n", pos) + 1;
					pos = file.indexOf("\n", pos) + 1;
					pos = file.indexOf("\n", pos) + 1;
					int boundaryLocation = file.indexOf(boundary, pos) - 4;
					int startPos = ((file.substring(0, pos)).getBytes()).length;
					int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
					
					String nome = saveFile.substring(0, saveFile.length() - 4);
					String extensao = saveFile.substring(saveFile.length() - 3, saveFile.length());
					
					String caminho = request.getSession().getServletContext().getRealPath("\\ra\\usuarios\\" + usuario.getLogin());
					
					if(extensao.equals("dae") || extensao.equals("kmz")) {
						caminho += "\\modelos\\";
					}
					else if (extensao.equals("pat")) {
						caminho += "\\marcadores\\";
					}
					else {
						caminho += "\\arquivos\\";
					}
	
					new File(caminho).mkdirs();
					
					saveFile = caminho + saveFile;
					
					if (new File(saveFile).exists()) {
						throw new Exception("Arquivo já existe.");
					}
					
					fileOut = new FileOutputStream(saveFile);
					fileOut.write(dataBytes, startPos, (endPos - startPos));
					fileOut.flush();
					fileOut.close();
					
					arquivo = new ArquivoBean();
					arquivo.setUsuario(usuario);
					arquivo.setNome(nome);
					arquivo.setExtensao(extensao);
					
					if(extensao.equals("dae") || extensao.equals("kmz")) {
						modelo = new ModeloBean();
						modelo.setUsuario(usuario);
						modelo.setArquivoModelo(arquivo);
						ModeloController.inserirModelo(modelo);
					}
					else if (extensao.equals("pat")) {
						marcador = new MarcadorBean();
						marcador.setUsuario(usuario);
						marcador.setArquivoMarcador(arquivo);
						MarcadorController.inserirMarcador(marcador);
					}
					else {
						ArquivoController.inserirArquivo(arquivo);
					}
	
					UtilitariosUI.mostrarMensagem("Arquivo enviado com sucesso!", request);
					
					getServletContext().getRequestDispatcher("/arquivo/FormUploadArquivo.jsp").forward(request, response);
				}
			}
		}
		catch (Exception exc) {			
			File file = new File(saveFile);
			
			if (file.exists()) {
				file.delete();
			}
			
			exc.printStackTrace();
			UtilitariosUI.mostrarMensagem(exc.getMessage(), request);
			getServletContext().getRequestDispatcher("/arquivo/FormUploadArquivo.jsp").forward(request, response);
		}
		finally {
			if (fileOut != null) {
				fileOut.flush();
				fileOut.close();
			}
		}
	}
}
