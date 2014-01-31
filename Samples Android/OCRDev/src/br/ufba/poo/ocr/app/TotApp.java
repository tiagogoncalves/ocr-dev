package br.ufba.poo.ocr.app;

import android.app.Application;
import android.graphics.Bitmap;

public class TotApp extends Application {
	private Bitmap bitmap;

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
}