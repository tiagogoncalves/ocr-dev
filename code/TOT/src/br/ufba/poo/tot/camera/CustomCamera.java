package br.ufba.poo.tot.camera;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import br.ufba.poo.tot.exceptions.UnavailableCameraException;

/**
 * Classe customizada que gerencia a câmera do Android
 * @author Equipe OCRDev
 *
 */
public class CustomCamera {
	
	
	/**
	 * Pega a instância da Câmera.
	 * @param context - contexto da aplicação.
	 * @return Instância da câmera.
	 * @throws UnavailableCameraException - Exceção que trata a disponibilidade da câmera.
	 */
	public static Camera getCameraInstance(Context context) throws UnavailableCameraException{
		Camera c = null;
		if(checkCameraHardware(context))
			c = Camera.open();
		else
			throw new UnavailableCameraException(context); 
		return c;
	}
	
	
	
	/**
	 * Método que checa se a câmera está disponível.
	 * @param context - contexto da aplicação.
	 * @return true se está disponível; false se não está disponível.
	 */
	public static boolean checkCameraHardware(Context context){
		if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			return true;
		}else{
			return false;
		}
		
	}

}
