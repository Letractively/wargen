package br.unic.wargen.gerador.servlet.arquivo;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.unic.wargen.gerador.bean.ArquivoBean;
import br.unic.wargen.gerador.bean.MarcadorBean;
import br.unic.wargen.gerador.bean.ModeloBean;
import br.unic.wargen.gerador.bean.UsuarioBean;
import br.unic.wargen.gerador.controller.ArquivoController;
import br.unic.wargen.gerador.controller.MarcadorController;
import br.unic.wargen.gerador.controller.ModeloController;
import br.unic.wargen.gerador.manager.SessionManager;
import br.unic.wargen.gerador.security.Autenticacao;
import br.unic.wargen.gerador.utils.ui.UtilitariosUI;

public class FormUploadArquivoServlet extends HttpServlet {
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UsuarioBean usuario = null;
		ModeloBean modelo = null;
		MarcadorBean marcador = null;
		ArquivoBean arquivo = null;
		String arquivoSalvar = null;
		
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
					
					arquivoSalvar = file.substring(file.indexOf("filename=\"") + 10);
					arquivoSalvar = arquivoSalvar.substring(0, arquivoSalvar.indexOf("\n"));
					arquivoSalvar = arquivoSalvar.substring(arquivoSalvar.lastIndexOf("\\")
					 + 1,arquivoSalvar.indexOf("\""));
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
					
					String nome = arquivoSalvar.substring(0, arquivoSalvar.length() - 4);
					String extensao = arquivoSalvar.substring(arquivoSalvar.length() - 3, arquivoSalvar.length());
					
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
					
					arquivoSalvar = caminho + arquivoSalvar;
					
					if (new File(arquivoSalvar).exists()) {
						throw new Exception("Arquivo já existe.");
					}
					
					fileOut = new FileOutputStream(arquivoSalvar);
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
			File file = new File(arquivoSalvar);
			
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
