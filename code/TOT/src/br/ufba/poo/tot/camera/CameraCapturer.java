package br.ufba.poo.tot.camera;

import java.io.File;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class CameraCapturer{
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	public static final String PHOTO_CAPTURED = "PHOTO_CAPTURED";
	private static final String dir = "ocrdev";
	
	private File mediaStorageDir;
	private File mediaFile;
	
	public CameraCapturer(){
		mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), dir);
		mediaFile = new File(mediaStorageDir.getPath() + File.separator +"img_temp" + ".jpg");
	}
	
	/** Create a file Uri for saving an image 
	 *  From http://developer.android.com/guide/topics/media/camera.html#saving-media
	 * */
	public Uri getOutputMediaFileUri(){
	      return Uri.fromFile(getOutputMediaFile());
	}
	
	/** Create a File for saving an image 
	 *  From http://developer.android.com/guide/topics/media/camera.html#saving-media
	 * */
	private File getOutputMediaFile(){
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("OCRDev", "failed to create directory");
	            return null;
	        }
	    }
	    return getMediaFile();
	}
	
	public boolean clear(){
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("OCRDev", "failed to create directory");
	            return false;
	        }
	    }
	    mediaStorageDir.delete();
	    return true;
	}

	public File getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}

}
