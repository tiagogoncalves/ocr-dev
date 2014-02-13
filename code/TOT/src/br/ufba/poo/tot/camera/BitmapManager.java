package br.ufba.poo.tot.camera;

import java.io.File;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class BitmapManager{
	private static final String dir = "ocrdev";

	private File mediaStorageDir;
	private File mediaFile;

	public BitmapManager(){
		setMediaStorageDir(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), dir));
		mediaFile = new File(getMediaStorageDir().getPath() + File.separator +"img_temp" + ".jpg");
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
	    if (! getMediaStorageDir().exists()){
	        if (! getMediaStorageDir().mkdirs()){
	            Log.d("OCRDev", "failed to create directory");
	            return null;
	        }
	    }
	    return getMediaFile();
	}

	public boolean clear(){
	    if (! getMediaStorageDir().exists()){
	        if (! getMediaStorageDir().mkdirs()){
	            Log.d("OCRDev", "failed to create directory");
	            return false;
	        }
	    }
	    getMediaStorageDir().delete();
	    return true;
	}

	public File getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}

	public File getMediaStorageDir() {
		return mediaStorageDir;
	}

	public void setMediaStorageDir(File mediaStorageDir) {
		this.mediaStorageDir = mediaStorageDir;
	}

}