package br.ufba.poo.tot.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.dialogs.LanguageListDialog;
import br.ufba.poo.tot.dialogs.events.OnLanguageSelectedListener;
import br.ufba.poo.tot.dialogs.events.OnTextExtractedListener;
import br.ufba.poo.tot.translator.TTranslator;

import com.gtranslate.Language;

/**
 * Esta Fragment é corresponde a tradução do texto transcrito pelo OCR
 * @author OCRDev
 *
 */
public class TranslateFragment extends Fragment implements OnLanguageSelectedListener,OnTextExtractedListener{
	public static final String TAG_LANGUAGE_FROM = "languageFrom";
	public static final String TAG_LANGUAGE_TO = "languageTo";
	private Button languageFrom;
	private Button languageTo;
	private TextView originalText;
	private TextView translatedText;
	
	private String idLangFrom;
	private String idLangTo;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.translate_fragment, container, false);
		loadComponents(view);
		return view;
	}

	/**
	 * Método que carrega os componentes da view
	 * @param view
	 */
	private void loadComponents(View view) {
		languageFrom = (Button) view.findViewById(R.id.language_from);
		Button change = (Button) view.findViewById(R.id.change);
		languageTo = (Button) view.findViewById(R.id.language_to);
		originalText = (TextView)view.findViewById(R.id.original_text);
		translatedText = (TextView)view.findViewById(R.id.translated_text);
		
		
		languageFrom.setText(TTranslator.getMapLanguage(getActivity()).get(Language.PORTUGUESE));
		languageTo.setText(TTranslator.getMapLanguage(getActivity()).get(Language.ENGLISH));
		languageFrom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogFragment languageListDialog = new LanguageListDialog();
			    languageListDialog.show(getFragmentManager(), TAG_LANGUAGE_FROM);
			}
		});
		
		change.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		languageTo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogFragment languageListDialog = new LanguageListDialog();
			    languageListDialog.show(getFragmentManager(), TAG_LANGUAGE_TO);
			}
		});
		
	}

	@Override
	public void setParamLanguage(String id, String label,String tag) {
		if(tag.equals(TAG_LANGUAGE_FROM)){
			idLangFrom=id;
			languageFrom.setText(TTranslator.getMapLanguage(getActivity()).get(id));
		}
		else if(tag.equals(TAG_LANGUAGE_TO)){
			idLangTo=id;
			languageTo.setText(TTranslator.getMapLanguage(getActivity()).get(id));
		}
	}

	@Override
	public void setTextExtracted(String text) {
		originalText.setText(text);
		TTranslator t = new TTranslator(TranslateFragment.this.getActivity(), text, idLangFrom, idLangTo);
		t.execute();
	}

	@Override
	public void setTextTranslated(String text) {
		translatedText.setText(text);
	}

}