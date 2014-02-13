package br.ufba.poo.tot.activities;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.camera.BitmapManager;
import br.ufba.poo.tot.fragments.CameraFragment;

/**
 * Classe da tela inicial e principal do aplicativo
 * @author Equipe OCRDev
 *
 */
public class TOTMainActivity extends FragmentActivity{
	public static final int PHOTO_CAPTURED=1;
	public static final String PHOTO="photo";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tot_main_activity);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  if (resultCode == RESULT_OK && requestCode == PHOTO_CAPTURED) {
		  CameraFragment cameraFragment = (CameraFragment)getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
		  Bitmap photo = data.getParcelableExtra(PHOTO);
		  cameraFragment.setPhotoCaptured(inflateBitmap());		  
	  }
	}

	private Bitmap inflateBitmap() {
		InputStream stream = null;
		Bitmap photo = null;
		BitmapManager bm = new BitmapManager();
		Uri fileUri = bm.getOutputMediaFileUri();
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


