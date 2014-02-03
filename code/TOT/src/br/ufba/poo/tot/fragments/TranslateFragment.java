package br.ufba.poo.tot.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.dialogs.LanguageListDialog;
import br.ufba.poo.tot.pojo.OCRFile;

import com.gtranslate.Language;

/**
 * Esta Fragment é corresponde a tradução do texto transcrito pelo OCR
 * @author OCRDev
 *
 */
public class TranslateFragment extends Fragment{
	private OCRFile ocrFile;
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
		ocrFile = new OCRFile(TranslateFragment.this.getActivity());
		Button languageFrom = (Button) view.findViewById(R.id.language_from);
		Button change = (Button) view.findViewById(R.id.change);
		Button languageTo = (Button) view.findViewById(R.id.language_to);
		
		
		languageFrom.setText(OCRFile.getMapLanguage(getActivity()).get(Language.PORTUGUESE));
		languageTo.setText(OCRFile.getMapLanguage(getActivity()).get(Language.ENGLISH));
		languageFrom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogFragment languageListDialog = new LanguageListDialog();
			    languageListDialog.show(getFragmentManager(), "dialog");
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
			    languageListDialog.show(getFragmentManager(), "dialog");
			}
		});
		
	}

}