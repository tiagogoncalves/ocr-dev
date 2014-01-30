package br.ufba.poo.tot.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import br.ufba.poo.tot.R;

/**
 * Classe da tela inicial e principal do aplicativo
 * @author Equipe OCRDev
 *
 */
public class TOTMainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tot_main_activity);
	}

}
