package br.ufba.poo.tot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.ufba.poo.tot.R;

/**
 * Esta Fragment é corresponde ao resultado da pesquisa pelo Google.
 * @author OCRDev
 *
 */
public class SearchFragment extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.search_fragment, container, false);
		return view;
	}

}