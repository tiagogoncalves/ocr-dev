package br.ufba.poo.tot.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.camera.CameraCapturer;
import br.ufba.poo.tot.camera.events.OnCameraListener;
import br.ufba.poo.tot.camera.events.OnOCRListener;
import br.ufba.poo.tot.ocr.OCRTreatment;

import com.gtranslate.Language;
import com.gtranslate.Translator;

/**
 * Este Fragment é corresponde a Barra Superior Principal do Aplicativo.
 * @author OCRDev
 *
 */
public class CameraFragment extends Fragment  implements OnCameraListener,OnOCRListener{
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	private Bitmap photo;
	ImageView initial;
	ImageView viewPhoto;
	
	/**
	 * Atualiza foto
	 */
	private void updatePhoto() {
		if(photo!=null){
			cropImage();
			viewPhoto.setImageBitmap(photo);
			initial.setVisibility(ImageView.GONE);
			viewPhoto.setVisibility(ImageView.VISIBLE);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.camera, container, false);
		loadComponents(view);
		return view;
	}

	/**
	 * Método que trata os componentes da view.
	 */
	private void loadComponents(View view) {
		initial = (ImageView) view .findViewById(R.id.initial);
		viewPhoto = (ImageView) view.findViewById(R.id.photo);

		initial.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});
		
		viewPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});
	}
	
	private void takePicture() {
		CameraCapturer cameraCapturer = new CameraCapturer();
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    fileUri = cameraCapturer.getOutputMediaFileUri(); 
	    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	    this.getActivity().startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}

	@Override
	public void setPhotoCaptured(Bitmap photo) {
		this.photo=photo;
		updatePhoto();
		OCRTreatment ocrT = new OCRTreatment(getActivity(), photo);
		ocrT.execute();
	}
	
	/**
	 * Recorta imagem.
	 */
	private void cropImage() {
		    int width = photo.getWidth();
		    int height = photo.getHeight();
		    int newWidth = 200;
		    int newHeight = 200;
		    // calculate the scale - in this case = 0.4f
		    float scaleWidth = ((float) newWidth) / width;
		    float scaleHeight = ((float) newHeight) / height;
		    Matrix matrix = new Matrix();
		    matrix.postScale(scaleWidth, scaleHeight);
		    photo = Bitmap.createBitmap(photo, 0, 0,width, height, matrix, true);
	}


	@Override
	public void setTextExtracted(String text) {
		Translator t = Translator.getInstance();
		String textTrans = t.translate(text, Language.ENGLISH, Language.PORTUGUESE);
	}


}