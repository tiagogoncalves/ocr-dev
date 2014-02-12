package br.ufba.poo.tot.camera.translaguage;

import java.io.ByteArrayOutputStream;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

class Preview extends SurfaceView implements SurfaceHolder.Callback {	

	private SurfaceHolder mHolder;
	private Camera mCamera;
	private Camera.Parameters mParameters;
	private byte[] mBuffer;

	public Preview(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Preview(Context context) {
		super(context);
		init();
	}

	@SuppressWarnings("deprecation")
	public void init() {
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	public Bitmap getPic(int x, int y, int width, int height) {
		System.gc(); 
		Bitmap b = null;
		Size s = mParameters.getPreviewSize();
		YuvImage yuvimage = new YuvImage(mBuffer, ImageFormat.NV21, s.width, s.height, null);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		yuvimage.compressToJpeg(new Rect(x, y, width, height), 100, outStream); // make JPG
		b = BitmapFactory.decodeByteArray(outStream.toByteArray(), 0, outStream.size()); // decode JPG
		if (b != null) {
		} else {
		}
		yuvimage = null;
		outStream = null;
		System.gc();
		return b;
	}

	private void updateBufferSize() {
		mBuffer = null;
		System.gc();
		int h = mCamera.getParameters().getPreviewSize().height;
		int w = mCamera.getParameters().getPreviewSize().width;
		int bitsPerPixel = ImageFormat.getBitsPerPixel( mCamera.getParameters().getPreviewFormat() );
		mBuffer = new byte[w * h * bitsPerPixel / 8];
	}

	public void surfaceCreated(SurfaceHolder holder) {
		try {
			mCamera = Camera.open(); // WARNING: without permission in Manifest.xml, crashes
		}
		catch (RuntimeException exception) {
			Toast.makeText(getContext(), "Camera broken, quitting :(", Toast.LENGTH_LONG).show();
		}

		try {
			mCamera.setPreviewDisplay(holder);
			updateBufferSize();
			mCamera.addCallbackBuffer(mBuffer); // where we'll store the image data
			mCamera.setPreviewCallbackWithBuffer(new PreviewCallback() {
				public synchronized void onPreviewFrame(byte[] data, Camera c) {

					if (mCamera != null) { // there was a race condition when onStop() was called..
						mCamera.addCallbackBuffer(mBuffer); // it was consumed by the call, add it back
					}
				}
			});
		} catch (Exception exception) {
			mCamera.release();
			mCamera = null;
			// TODO: add more exception handling logic here
		}
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		mCamera.stopPreview();
		mCamera.release();
		mCamera = null;
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
		try {
			mParameters = mCamera.getParameters();
			mParameters.set("orientation","landscape");
			for (Integer i : mParameters.getSupportedPreviewFormats()) {
			} 

			List<Size> sizes = mParameters.getSupportedPreviewSizes();
			for (Size size : sizes) {
			}
			mCamera.setParameters(mParameters); // apply the changes
		} catch (Exception e) {
		}

		updateBufferSize(); // then use them to calculate

		Size p = mCamera.getParameters().getPreviewSize();
		mCamera.startPreview();
	}

	public Parameters getCameraParameters(){
		return mCamera.getParameters();
	}

	public void setCameraFocus(AutoFocusCallback autoFocus){
		mCamera.autoFocus(autoFocus);
	}

	public void setFlash(boolean flash){
		if (flash){
			mParameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
			mCamera.setParameters(mParameters);
		}
		else{
			mParameters.setFlashMode(Parameters.FLASH_MODE_OFF);
			mCamera.setParameters(mParameters);
		}
	}
}
