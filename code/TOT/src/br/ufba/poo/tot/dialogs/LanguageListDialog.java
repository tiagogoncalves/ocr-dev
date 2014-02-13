package br.ufba.poo.tot.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.fragments.TranslateFragment;
import br.ufba.poo.tot.translator.TTranslator;

/**
 * Classe que implementa um Dialog(espécie de janela pop-up) das línguas a serem escolhidas
 * @author Equipe OCRDev
 *
 */
public class LanguageListDialog extends DialogFragment {

	private LanguageAdapter languageAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.language_list_dialog, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ListView languageList = (ListView) v.findViewById(R.id.language_list);
        languageAdapter = new LanguageAdapter(LanguageListDialog.this.getActivity(), TTranslator.getMapLanguage(LanguageListDialog.this.getActivity()));
        languageList.setAdapter(languageAdapter);
        
        languageList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> av, View v, int pos,long id) {
				TranslateFragment tf = (TranslateFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.translate_fragment);
				tf.setParamLanguage(getKey(languageAdapter.getLanguages().get(pos)), languageAdapter.getLanguages().get(pos),LanguageListDialog.this.getTag());
				LanguageListDialog.this.dismiss();
			}
		});
        
        return v;
    }
    
    private String getKey(String value) {
		for(Object l: TTranslator.getMapLanguage(LanguageListDialog.this.getActivity()).keySet().toArray())
			if( ((String)l).equals(value) )
				return (String)l;
		return "";
	}
}