package br.com.wargen.interfaces;

public interface IAsyncTaskListener {

	public void onTaskExecutou(Object objeto);
	public void onTaskFalhou(String mensagem);
	public void onTaskFalhou(Exception exc);
	
}
