package br.ufba.poo.tot.dialogs;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.pojo.Text;

/**
 * Classe que implementa um Dialog(espécie de janela pop-up) das línguas a serem escolhidas
 * @author Equipe OCRDev
 *
 */
public class LanguageListDialog extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.language_list_dialog, container, false);
        ListView languageList = (ListView) v.findViewById(R.id.language_list);
        LanguageAdapter languageAdapter = new LanguageAdapter(LanguageListDialog.this.getActivity(), Text.getMapLanguage(LanguageListDialog.this.getActivity()));
        languageList.setAdapter(languageAdapter);
        return v;
    }
}