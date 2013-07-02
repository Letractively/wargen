package br.com.wargen.activity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;
import edu.dhbw.andar.pub.CustomActivity;
import edu.dhbw.andar.pub.CustomObject;
import edu.dhbw.andar.pub.CustomRenderer;
import br.com.wargen.R;
import br.com.wargen.UtilitariosUI;
import br.com.wargen.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
@SuppressWarnings("unused")
public class PrincipalActivity extends AndARActivity {

	private final int MENU_SCREENSHOT = 0;

	CustomObject someObject;
	ARToolkit artoolkit;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		CustomRenderer renderer = new CustomRenderer();//optional, may be set to null
		super.setNonARRenderer(renderer);//or might be omited
		try {
			artoolkit = super.getArtoolkit();
			someObject = new CustomObject
					("test", "hiro.patt", 80.0, new double[]{0,0});
			artoolkit.registerARObject(someObject);
			someObject = new CustomObject
					("test", "android.patt", 80.0, new double[]{0,0});
			someObject = new CustomObject
					("test", "marcador.patt", 80.0, new double[]{0,0});
			artoolkit.registerARObject(someObject);
			//someObject = new CustomObject
			//("test", "barcode.patt", 80.0, new double[]{0,0});
			//artoolkit.registerARObject(someObject);
			
		} catch (AndARException ex){
			//handle the exception, that means: show the user what happened
			UtilitariosUI.MensagemAlerta(this, ex.getMessage());
		}			
	}

	/**
	 * Inform the user about exceptions that occurred in background threads.
	 * This exception is rather severe and can not be recovered from.
	 * Inform the user and shut down the application.
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		Log.e("AndAR EXCEPTION", ex.getMessage());
		finish();
	}
	
}
