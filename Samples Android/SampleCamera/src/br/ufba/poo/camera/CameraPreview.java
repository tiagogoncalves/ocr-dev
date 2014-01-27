package br.ufba.poo.camera;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * A classe CameraPreview mostra a imagem vinda da câmera.Implementa a
 * interface Callback que captura eventos de criação e
 * destruição da view.
 * @author Equipe OCRDev
 *
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
	private SurfaceHolder holder;
	private Camera camera;
	private Size previewSize;
	private List<Size> supportedPreviewSizes;
	
	public CameraPreview(Context context,Camera camera) {
		super(context);
		this.camera=camera;
		
		//Notificação de criação e destruição da SurfaceView
		holder = getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//TODO eu sei eu sei
	}

	/**
	 * É chamado quando a surface sofre alguma alteração.
	 */
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
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
		
		Camera.Parameters parameters = camera.getParameters();        
        Size previewSize = getPreviewSize();
        parameters.setPreviewSize(previewSize.width, previewSize.height);                
        camera.setParameters(parameters);
		
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (Exception e) {
			Log.e(CameraPreview.class.getSimpleName(), "Error starting camera preview: "+e.getMessage());
		}
		
	}

	/**
	 * Criação da Surface. 
	 */
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		try {
			camera.setPreviewDisplay(holder);
			camera.startPreview();
		} catch (IOException e) {
			Log.e(CameraPreview.class.getSimpleName(), "Error setting camera preview: "+e.getMessage());
		}
	}

	/**
	 * É chamado quando a surface é destruída.
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Tratar se necessário
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		final int width = resolveSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);
		final int height = resolveSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);
		setMeasuredDimension(width, height);

		if (supportedPreviewSizes != null) {
			previewSize = getOptimalPreviewSize(supportedPreviewSizes, width,
					height);
		}
	}

	public void setSupportedPreviewSizes(List<Size> supportedPreviewSizes) {
		this.supportedPreviewSizes = supportedPreviewSizes;
	}

	public Size getPreviewSize() {
		return previewSize;
	}

	private Size getOptimalPreviewSize(List<Size> sizes, int width, int height) {
		Size optimalSize = null;

		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) height / width;

		// Try to find a size match which suits the whole screen minus the menu
		// on the left.
		for (Size size : sizes) {
			if (size.height != width)
				continue;
			double ratio = (double) size.width / size.height;
			if (ratio <= targetRatio + ASPECT_TOLERANCE
					&& ratio >= targetRatio - ASPECT_TOLERANCE) {
				optimalSize = size;
			}
		}

		// If we cannot find the one that matches the aspect ratio, ignore the
		// requirement.
		if (optimalSize == null) {
			// TODO : Backup in case we don't get a size.
		}

		return optimalSize;
	}



}
