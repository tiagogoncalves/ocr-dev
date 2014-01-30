package br.ufba.poo.tot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.ufba.poo.tot.R;

/**
 * Esta Fragment é corresponde a tradução do texto transcrito pelo OCR
 * @author OCRDev
 *
 */
public class TranslateFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.translate_fragment, container, false);
		return view;
	}

}