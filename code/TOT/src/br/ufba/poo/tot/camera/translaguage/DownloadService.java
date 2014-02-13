package br.ufba.poo.tot.camera.translaguage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class DownloadService extends Service {

	//private final String TAG = "TESTESTESTEST";
	private String mDownloadPath = "http://tesseract-ocr.googlecode.com/files/";
	private File mPath = new File(Environment.getExternalStorageDirectory(),"translanguage/tessdata/");
	private int mI;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		//Log.i(TAG,"Service onStart");
		Bundle b = intent.getExtras();
		mI = b.getInt("lang");
		super.onStart(intent, startId);
		checkFolder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//Log.i(TAG,"Service onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	public void onCreate() {
		super.onCreate();
		//Log.i(TAG, "Service onCreate()");
		//checkFolder();
		//onDestroy();
	}

	private void checkFolder() {
		//Log.i(TAG,"I am checking folder");
		if (!mPath.exists()){
			mPath.mkdirs();
			//Log.i(TAG,"Making folder");
		}
		//for (int i=0; i < Languages.mLanguages.length; i++){
			//mI = i;
			//check the unzipped file does not exist in the folder
			File fileToDownload = new File(mPath,Languages.mLanguages2[mI]);
			//Log.i(TAG,"File :"+mDownloadPath+Languages.mLanguages2[mI]);
			//Log.i(TAG,"Does it exist? "+fileToDownload.exists());
			//check if file exists, if it doesnt, dnld files
			if (!fileToDownload.exists()){
				//Log.i(TAG,"File does not exist, we download it");
				// sends the zipped file name to download to be downloaded.
				startDownload();
			}
			else{
				if (fileToDownload.length()<Languages.mLanguageSizes[mI]){
					startDownload();
				}
			}
		//}
		//onDestroy();
	}
	
	private void startDownload(){
		Thread downloadFile = new Thread( new Runnable() {
			public void run() {
				//if (!new File(mPath,Languages.mLanguages[mI]).exists()){
					download(Languages.mLanguages[mI]);
				//}

				//check files exist after download
				if (new File(mPath,Languages.mLanguages[mI]).exists()){
					//Log.i(TAG,"File was downloaded, now unzip");
					//unzip them
					String unzippedFile = unzipFile(new File(mPath,Languages.mLanguages[mI]).toString(),Languages.mLanguages2[mI]);
					if (new File(mPath,Languages.mLanguages[mI]).exists()){
						//Log.i(TAG,"File unzipped, now erase zip file");
						new File(mPath,Languages.mLanguages[mI]).delete();
					}
				}
				if (new File(mPath,Languages.mLanguages[mI]).exists()){
					//Log.i(TAG,"Double checking, File unzipped, now erase zip file");
					new File(mPath,Languages.mLanguages[mI]).delete();
				}
				if (mI >= Languages.mLanguages.length){
				}
			}
		});
		downloadFile.start();
	}
	
	private String unzipFile(String source, String lang) {
		GZIPInputStream in;
		String target = null;
		try {
			in = new GZIPInputStream(new FileInputStream(source));

			target = mPath.toString()+"/"+lang;
			OutputStream out = new FileOutputStream(target);

			// Transfer bytes from the compressed file to the output file
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			// Close the file and stream
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return target;

	}

	private void download(String language){
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

			//Log.i(TAG,"Downloading file");
			/**this is the file to be downloaded from the internet*/
			////Log.i(TAG,"encoder is: "+URLEncoder.encode(mDownloadPath+language));
			//URI url = URI.create(URLEncoder.encode(mDownloadPath+language));
			URI url = URI.create(mDownloadPath+language);
			HttpClient client = new DefaultHttpClient();

			HttpUriRequest request = new HttpGet(url);

			HttpResponse response = client.execute(request);

			/**this is the path of where you like to put the file. */
			File SDCardRoot = Environment.getExternalStorageDirectory();

			/**name of the file that will be going inside sdcard*/
			File gzFile = new File(mPath,language);

			/**FileOutputStream will write to the sdcard*/
			FileOutputStream fileout = new FileOutputStream(gzFile);

			/**this will be used in reading the data from the internet*/
			InputStream inputStream = response.getEntity().getContent();
			Header contentEncoding = response.getFirstHeader("Content-Encoding");
			if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
				inputStream = new GZIPInputStream(inputStream);
			}

			/**create a buffer*/
			byte[] buffer = new byte[1024];

			/**used to store a temporary size of the buffer*/
			int bufferLength = 0;
			/**write to the file using the buffer and the write method of FileOutputStream*/
			while ( (bufferLength = inputStream.read(buffer)) > 0 ) {
				fileout.write(buffer, 0, bufferLength);
			}
			/**do not forget to close the file*/
			fileout.close();

			/**catch the errors*/
		} catch (MalformedURLException e) {
			//Log.i(TAG,"malformed URL");
			e.printStackTrace();
		} catch (IOException e) {
			//Log.i(TAG,"IOException");
			e.printStackTrace();
		}
		//Log.i(TAG,"finished downloading");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		//Log.i(TAG, "Service destroying");
	}

}
