package br.ufba.poo.tot.dialogs.events;

/**
 * Interface que gerencia o texto extraído do OCR
 * 
 * @author Equipe OCR-dev
 * 
 */
public interface OnTextExtractedListener {

	/**
	 * Retorna texto
	 * @param text - texto
	 */
	public void setTextExtracted(String text);
	
	/**
	 * Retorna texto traduzido
	 * @param text
	 */
	public void setTextTranslated(String text);

}
