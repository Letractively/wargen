package br.com.wargen;

import android.app.Activity;
import br.com.wargen.interfaces.IAsyncTaskListener;

public class ActivityPersonalizada extends Activity implements IAsyncTaskListener{

	@Override
	public void onTaskExecutou(Object objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskFalhou(String mensagem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTaskFalhou(Exception exc) {
		// TODO Auto-generated method stub
		
	}

}
