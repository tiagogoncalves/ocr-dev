package br.ufba.poo.ocr.app;

import android.app.Application;

/**
 * Classe que implementa o padrão Singleton. Sua instância é única e
 * presente durante toda a execução do sistema.
 * @author Equipe OCRDev
 *
 */
public class TotApp extends Application {
	private String languageOriginalDefault;
	private String languageTranslateDefault;
	
	public String getLanguageOriginalDefault() {
		return languageOriginalDefault;
	}
	public void setLanguageOriginalDefault(String languageOriginalDefault) {
		this.languageOriginalDefault = languageOriginalDefault;
	}
	public String getLanguageTranslateDefault() {
		return languageTranslateDefault;
	}
	public void setLanguageTranslateDefault(String languageTranslateDefault) {
		this.languageTranslateDefault = languageTranslateDefault;
	}
	
}