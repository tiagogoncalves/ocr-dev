package com.ufba.syol;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.gtranslate.Language;
import com.gtranslate.Translator;

public class SyolMainActivity extends Activity {

	private Bitmap bitmap;
	private TextView txtView,txtViewTrans;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_syol_main);
		loadBitmap();
		startCamera();
		
	}

	private void startCamera() {
		Button btnStart = (Button) findViewById(R.id.button_start);
		
		txtView= (TextView) findViewById(R.id.textView);
		txtViewTrans= (TextView) findViewById(R.id.textViewTranslated);
		
		btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				runOCR();
				
//				Intent it = new Intent(SyolMainActivity.this,PhotoCapture.class);
//				startActivity(it);
			}

			private void runOCR() {
				TessBaseAPI baseApi = new TessBaseAPI();
				baseApi.init("/mnt/sdcard/tesseract", "eng");
				baseApi.setImage(bitmap);
				String recognizedText = baseApi.getUTF8Text();
				baseApi.end();
				
				txtView.setText(recognizedText);
				txtViewTrans.setText(translate(recognizedText));
				
			}
		});
		
	}
	
	private static String translate(String entry){
		String t = "";
		Translator translate = Translator.getInstance();
		t = translate.translate(entry, Language.ENGLISH, Language.PORTUGUESE);
		return t;
	}
	
	private void loadBitmap(){
		// Get the AssetManager
	    AssetManager manager = getAssets();

	    // read a Bitmap from Assets
	    InputStream open = null;
	    try {
	      open = manager.open("example.jpg");
	      BitmapFactory.Options opts = new BitmapFactory.Options();
	      opts.inPreferredConfig = Config.ARGB_8888;
	      bitmap = BitmapFactory.decodeStream(open, null, opts);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (open != null) {
	        try {
	          open.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    } 
		
	}

}
