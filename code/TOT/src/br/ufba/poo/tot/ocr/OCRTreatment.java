package br.ufba.poo.tot.ocr;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.fragments.CameraFragment;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * Classe que realiza o tratamento OCR da imagem. Extende de AssyncTask por ser
 * uma atividade assíncrona.
 * @author Equipe OCR-Dev
 *
 */
public class OCRTreatment extends AsyncTask{
	
	public static final String ENG = "eng";
	@SuppressLint("SdCardPath")
	public static final String DATA_PATH = "/mnt/sdcard/tesseract";
	
	private CameraFragment cameraFragment;
	private ProgressDialog progress;
	private Bitmap photo;
	private Context context;
	private String textResult;
	
	public OCRTreatment(Context context,Bitmap photo){
		this.context=context;
		this.photo=photo;
		cameraFragment = (CameraFragment)((FragmentActivity) context).getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
	}
	
	/**
	 * Método de pré-execução.
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = new ProgressDialog(context);
		progress.setTitle(context.getResources().getString(R.string.title_wait));
		progress.setMessage(context.getResources().getString(R.string.body_wait));
		progress.show();
	}
	
	/**
	 * Executa em plano de fundo. Aqui ocorre a parte crítica do processamento. 
	 */
	@Override
	protected Object doInBackground(Object... params) {
		setTextResult(runOCR(context, photo));
		return null;
	}
	
	/**
	 * Ùltimo método a ser executado.
	 */
	@Override
	protected void onPostExecute(Object result) {
		super.onPostExecute(result);
		progress.dismiss();
	}
	
	/**
	 * Executa a etapa de extração de texto da imagem.
	 * @param context - contexto
	 * @param photo - photo do tratamento
	 * @return String do texto extraído da imagem
	 */
	private String runOCR(Context context, Bitmap photo) {
		TessBaseAPI baseApi = new TessBaseAPI();
		baseApi.init(DATA_PATH, ENG);
		baseApi.setImage(photo);
		String recognizedText = baseApi.getUTF8Text();
		baseApi.end();
		cameraFragment.setTextExtracted(recognizedText);
		return recognizedText;
	}

	public String getTextResult() {
		return textResult;
	}

	public void setTextResult(String textResult) {
		this.textResult = textResult;
	}
	
}
