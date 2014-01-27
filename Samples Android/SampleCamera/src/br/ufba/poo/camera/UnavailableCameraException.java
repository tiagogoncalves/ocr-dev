package br.ufba.poo.camera;

/**
 * Exceção de câmera indisponível
 * @author Equipe OCRDev
 *
 */
public class UnavailableCameraException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UnavailableCameraException(){
		super("Camera unavailable!");
	}

}
