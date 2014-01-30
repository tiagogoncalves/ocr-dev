package br.ufba.poo.tot.exceptions;

import android.content.Context;
import br.ufba.poo.tot.R;

/**
 * Exceção de câmera indisponível
 * @author Equipe OCRDev
 *
 */
public class UnavailableCameraException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UnavailableCameraException(Context context){
		super(context.getResources().getString(R.string.unavailable_camera));
	}

}
