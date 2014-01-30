package br.ufba.poo.camera;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/**
 * A classe CameraPreview mostra a imagem vinda da câmera.Implementa a
 * interface Callback que captura eventos de criação e
 * destruição da view.
 * Código baseado no tutorial da Google ...
 * @author Equipe OCRDev
 *
 */
public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
	private SurfaceHolder holder;
	private Camera camera;
	
	public CameraPreview(Context context,Camera camera) {
		super(context);
		this.camera=camera;
		
		//Notificação de criação e destruição da SurfaceView
		holder = getHolder();
		holder.addCallback(this);
//		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);//TODO eu sei eu sei
		
	}
	
	/**
	 * Escolhe o modo do foco da câmera.
	 */
	private void setFocusMode(String focusMode){
		Camera.Parameters params = camera.getParameters();
		params.setFocusMode(focusMode);
		camera.setParameters(params);
	}
	
	private void focalizeArea(){
		Camera.Parameters params = camera.getParameters();

//		if (params.getMaxNumMeteringAreas() > 0){ // check that metering areas are supported
		    List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
//		    Rect areaRect1 = new Rect(0, 10, 10, 10);    // specify an area in center of image
//		    meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
		    Rect areaRect2 = new Rect(0, 0, 0, 0);  // specify an area in upper right of image
		    meteringAreas.add(new Camera.Area(areaRect2, 400)); // set weight to 40%
		    params.setMeteringAreas(meteringAreas);
//		}
		camera.setParameters(params);
		
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
		setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
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
	




}
