package br.ufba.poo.tot.activities;

import android.content.Context;
import android.graphics.Bitmap;

import com.googlecode.tesseract.android.TessBaseAPI;

public class OCRTreatment {
	
	public static void runOCR(Context context, Bitmap photo) {
//		OCRFile ocrFile = ((TOTApp)context.getApplicationContext()).getOcrFile();
//		if(ocrFile!=null){
			TessBaseAPI baseApi = new TessBaseAPI();
			baseApi.init("/mnt/sdcard/tesseract", "eng");
			baseApi.setImage(photo);
			String recognizedText = baseApi.getUTF8Text();
			baseApi.end();	
//		}
	}
}
