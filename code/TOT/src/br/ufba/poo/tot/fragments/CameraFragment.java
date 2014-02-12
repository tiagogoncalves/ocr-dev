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
//		scaleImage(viewPhoto, dpToPx(300));
		OCRTreatment ocrT = new OCRTreatment(getActivity(), photo);
		ocrT.execute();
	}
	
	private void scaleImage(ImageView view, int boundBoxInDp) {
		Drawable drawing = view.getDrawable();
		Bitmap bitmap = ((BitmapDrawable) drawing).getBitmap();

		// Get current dimensions
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		float xScale = ((float) boundBoxInDp) / width;
		float yScale = ((float) boundBoxInDp) / height;
		float scale = (xScale <= yScale) ? xScale : yScale;

		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);

		Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,matrix, true);

		BitmapDrawable result = new BitmapDrawable(scaledBitmap);
		width = scaledBitmap.getWidth();
		height = scaledBitmap.getHeight();

		// Apply the scaled bitmap
		view.setImageDrawable(result);

		// Now change ImageView's dimensions to match the scaled image
		LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
		params.width = width;
		params.height = height;
		view.setLayoutParams(params);
	}

	private int dpToPx(int dp) {
		float density = getActivity().getResources().getDisplayMetrics().density;
		return Math.round((float) dp * density);
	}

	@Override
	public void setTextExtracted(String text) {
		Translator t = Translator.getInstance();
		String textTrans = t.translate(text, Language.ENGLISH, Language.PORTUGUESE);
	}


}