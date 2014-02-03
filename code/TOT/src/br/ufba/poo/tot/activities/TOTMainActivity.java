package br.ufba.poo.tot.activities;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.camera.CameraCapturer;
import br.ufba.poo.tot.fragments.CameraFragment;

/**
 * Classe da tela inicial e principal do aplicativo
 * @author Equipe OCRDev
 *
 */
public class TOTMainActivity extends FragmentActivity{

	private CameraCapturer cameraCapturer;
	private CameraFragment cameraFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tot_main_activity);
//		((TOTApp)getApplication()).setOcrFile(new OCRFile(TOTMainActivity.this));
		cameraFragment = (CameraFragment)getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
		cameraCapturer = new CameraCapturer();
	}
	
	
	
	/**
	 * Método que é invocado no retorno da activity invocada.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CameraCapturer.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			cameraFragment.setPhotoCaptured(inflateBitmap());
		}
	}
	
	/**
	 * Método que infla o bitmap vindo do stream da câmera
	 * @return
	 */
	private Bitmap inflateBitmap() {
		InputStream stream = null;
		Bitmap photo = null;
		Uri fileUri = cameraCapturer.getOutputMediaFileUri();
		try {
			stream = getContentResolver().openInputStream(fileUri);
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inPreferredConfig = Config.ARGB_8888;
			photo = BitmapFactory.decodeStream(stream, null, opts);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return photo;
	}

}

