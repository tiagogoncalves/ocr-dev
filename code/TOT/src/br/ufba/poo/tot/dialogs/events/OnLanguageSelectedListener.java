package br.ufba.poo.tot.dialogs.events;


/**
 * Interface que gerencia linguagem selecionada do Dialog de línguas
 * @author Equipe OCR-dev
 *
 */
public interface OnLanguageSelectedListener {
	
	/**
	 *Retorna língua  
	 * @param id - id do Map de Línguas em OCRFile
	 * @param label - Rótulo da língua
	 * @param tag - tag da chamada do Dialog
	 */
	public void setParamLanguage(String id,String label,String tag);
}
