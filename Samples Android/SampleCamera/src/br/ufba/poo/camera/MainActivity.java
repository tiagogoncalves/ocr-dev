package br.ufba.poo.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

	private Camera camera;
	private CameraPreview preview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		try {
			camera = CustomCamera.getCameraInstance(getApplicationContext());
			preview = new CameraPreview(MainActivity.this.getApplicationContext(), camera);
			FrameLayout layPreview = (FrameLayout) findViewById(R.id.camera_preview);
			layPreview.addView(preview);
		} catch (UnavailableCameraException e) {
			Log.e(MainActivity.class.getName(), e.getMessage());
		}
		
	}

}
