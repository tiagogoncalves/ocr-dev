package br.ufba.poo.tot.pojo;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import br.ufba.poo.tot.R;

import com.gtranslate.Language;

/**
 * P.O.J.O. do texto lido pela API OCR e o traduzido pela API Google Translate(non-official)
 * @author Equipe OCRDev
 *
 */
public class Text {
	
	private String original;
	private int originalLanguage;
	private String translated;
	private int translatedLanguage;
	
	
	public Text(Context context){
		getMapLanguage(context);
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public int getOriginalLanguage() {
		return originalLanguage;
	}
	public void setOriginalLanguage(int originalLanguage) {
		this.originalLanguage = originalLanguage;
	}
	public String getTranslated() {
		return translated;
	}
	public void setTranslated(String translated) {
		this.translated = translated;
	}
	public int getTranslatedLanguage() {
		return translatedLanguage;
	}
	public void setTranslatedLanguage(int translatedLanguage) {
		this.translatedLanguage = translatedLanguage;
	}
	
	public static Map<String,String> getMapLanguage(Context context){
		Map<String,String> mapLanguage = new HashMap<String, String>();
		mapLanguage.put(Language.ENGLISH, context.getResources().getString(R.string.english));
		mapLanguage.put(Language.FRENCH, context.getResources().getString(R.string.french));
		mapLanguage.put(Language.PORTUGUESE, context.getResources().getString(R.string.portuguese));
		mapLanguage.put(Language.GERMAN, context.getResources().getString(R.string.german));
		mapLanguage.put(Language.SPANISH, context.getResources().getString(R.string.spanish));
		return mapLanguage;
	} 

}
