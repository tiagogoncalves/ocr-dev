package br.ufba.poo.ocr;

import java.io.FileNotFoundException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.TextView;
import br.ufba.poo.ocr.camera.Picture;

import com.gtranslate.Language;
import com.gtranslate.Translator;

public class OCRDevMainActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	private Bitmap photo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ocrdev_main);
		takePicture();
	}
	
	private void takePicture() {
		Picture picture = new Picture();
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    fileUri = picture.getOutputMediaFileUri(); 
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    	 InputStream stream = null;
	    	    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK)
	    	      try {
	    	        // recyle unused bitmaps
	    	        if (photo != null) {
	    	          photo.recycle();
	    	        }
	    	        stream = getContentResolver().openInputStream(fileUri);
	    	        
	    	        BitmapFactory.Options opts = new BitmapFactory.Options();
	    		      opts.inPreferredConfig = Config.ARGB_8888;
	    		      
	    		      
	    	        photo = BitmapFactory.decodeStream(stream,null, opts);
	    	        
	    	        runOCR();
//	    	        ImageView iv = (ImageView) findViewById(R.id.imageView);
//	    	        iv.setImageBitmap(photo);

	    	      } catch (FileNotFoundException e) {
	    	        e.printStackTrace();
	    	      }
	    	    
	    	    
	}
	
	private void runOCR() {
		TessBaseAPI baseApi = new TessBaseAPI();
		baseApi.init("/mnt/sdcard/tesseract", "eng");
		baseApi.setImage(photo);
		String recognizedText = baseApi.getUTF8Text();
		baseApi.end();
		
		TextView txtView = (TextView) findViewById(R.id.textView);
		txtView.setText(recognizedText);
		
		TextView txtTrans = (TextView)findViewById(R.id.trans);
//		txtTrans.setText(translate(recognizedText));		
	}
	
	private static String translate(String entry){
		String t = "";
		Translator translate = Translator.getInstance();
		t = translate.translate(entry, Language.ENGLISH, Language.PORTUGUESE);
		return t;
	}
	
}
