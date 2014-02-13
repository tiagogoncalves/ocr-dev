package br.ufba.poo.tot.translator;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.fragments.TranslateFragment;

import com.gtranslate.Language;
import com.gtranslate.Translator;

/**
 * Classe que gerencia as traduções
 * @author Equipe OCR-Dev
 *
 */
public class TTranslator extends AsyncTask{
	private ProgressDialog progress;
	private Context context;
	private String text;
	private String from;
	private String to;
	
	public TTranslator(Context context,String text,String from,String to){
		this.context=context;
		this.text=text;
		this.from=from;
		this.to=to;
	}
	
	public TTranslator(Context context,String text,String to){
		this.context=context;
		this.text=text;
		this.to=to;
	}
	
	
	
	/**
	 * Método de pré-execução.
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = new ProgressDialog(context);
		progress.setTitle(context.getResources().getString(R.string.title_wait));
		progress.setMessage(context.getResources().getString(R.string.body_wait_trans));
		progress.show();
	}
	
	/**
	 * Executa em plano de fundo. Aqui ocorre a parte crítica do processamento. 
	 */
	@Override
	protected Object doInBackground(Object... params) {
		if(!isOnline()){
			text=null;
		}
		if(text!=null && to!=null)
			if(from!=null)
				translate(text, from,to);
			else 
				translate(text,to);
		return null;
	}
	
	/**
	 * Ùltimo método a ser executado.
	 */
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		TranslateFragment tf = (TranslateFragment)((FragmentActivity) context).getSupportFragmentManager().findFragmentById(R.id.translate_fragment);
		tf.setTextTranslated(text);
		progress.dismiss();
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
	
	
	/**
	 * Método que realiza a tradução
	 * @param text - texto a ser traduzido
	 * @param from - língua de origem.Ver com.gtranslate.Language
	 * @param to - língua destino. Ver com.gtranslate.Language
	 */
	private void translate(String text,String from,String to){
		Translator t = Translator.getInstance();
		this.text= t.translate(text+" ", from, to);
	}
	
	/**
	 * Método que realiza a tradução. A detecção da língua de origem é automática
	 * @param text - texto a ser traduzido
	 * @param to - língua destino. Ver com.gtranslate.Language
	 */
	private void translate(String text,String to){
		Translator t = Translator.getInstance();
		text= t.translate(text, t.detect(text), to);
	}
	
	
	/**
	 * Verifica a conexão do smartphone
	 * @return true or false
	 */
	private boolean isOnline() {
	    ConnectivityManager cm =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}

}
