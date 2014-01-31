package br.ufba.poo.tot.camera;

import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;


/**
 * A classe CameraPreview mostra a imagem vinda da câmera.Implementa a
 * interface Callback que captura eventos de criação e
 * destruição da view.
 * @author Equipe OCRDev
 *
 */
@SuppressLint("ViewConstructor")
public class CameraPreview extends ViewGroup implements SurfaceHolder.Callback {
    SurfaceView surfaceView;
    SurfaceHolder holder;
    Camera camera;

    public CameraPreview(Context context, Camera camera) {
        super(context);
        this.camera = camera;

        surfaceView = new SurfaceView(context);
        addView(surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(this);
    }



    public void surfaceCreated(SurfaceHolder holder) {
    	try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			Log.e(CameraPreview.class.getSimpleName(), "Error setting camera preview: "+e.getMessage());
		}
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
        }
    }


    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		// TODO Cuidar da rotação ou mudança da surface
		if(holder.getSurface()==null){
			//TODO Surface não existe
		}
		
		try {
			camera.stopPreview();
		} catch (Exception e) {
			//TODO quando tenta parar uma preview inexistente
		}
		
		//TODO tratar tamanho e redimensionamento, rotação ou reformatação aqui
		//Caso queira redimensionar verificar em getSuporttedPreviewSizes() e 
		//utilizar setPreviewSize();
		setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (Exception e) {
			Log.e(CameraPreview.class.getSimpleName(), "Error starting camera preview: "+e.getMessage());
		}
		
    }

    /**
	 * Escolhe o modo do foco da câmera.
	 */
	private void setFocusMode(String focusMode){
		Camera.Parameters params = camera.getParameters();
		params.setFocusMode(focusMode);
		camera.setParameters(params);
	}


	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
	}

}