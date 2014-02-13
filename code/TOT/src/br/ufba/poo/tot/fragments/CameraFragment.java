package br.ufba.poo.tot.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.activities.TOTMainActivity;
import br.ufba.poo.tot.camera.events.OnCameraListener;
import br.ufba.poo.tot.camera.events.OnOCRListener;
import br.ufba.poo.tot.camera.translaguage.CameraPreview;
import br.ufba.poo.tot.dialogs.SamplesImagesDialog;
import br.ufba.poo.tot.ocr.OCRTreatment;

/**
 * Este Fragment é corresponde a Barra Superior Principal do Aplicativo.
 * @author OCRDev
 *
 */
public class CameraFragment extends Fragment  implements OnCameraListener,OnOCRListener{
	private Bitmap photo;
	private ImageView initial;
	private ImageView viewPhoto;
	
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
		
		//Easter egg;
		initial.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View arg0) {
				DialogFragment sid = new SamplesImagesDialog();
			    sid.show(getFragmentManager(), "sid");				
				return false;
			}
		});
		
		
		viewPhoto.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				takePicture();
			}
		});
		// Easter egg;
		viewPhoto.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View arg0) {
				DialogFragment sid = new SamplesImagesDialog();
			    sid.show(getFragmentManager(), "sid");
				return false;
			}
		});
		
	}
	
	/**
	 * Método que chama a a ação de tirar foto
	 * Customizada - from Translanguage
	 */
	private void takePicture() {
		Intent intent = new Intent(getActivity(),CameraPreview.class);
		this.getActivity().startActivityForResult(intent,TOTMainActivity.PHOTO_CAPTURED);
	}
	

	@Override
	public void setPhotoCaptured(Bitmap photo) {
		this.photo=photo;
		updatePhoto();
		OCRTreatment ocrT = new OCRTreatment(getActivity(), photo);
		ocrT.execute();
	}


	@Override
	public void setTextExtracted(String text) {
		TranslateFragment tf = (TranslateFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.translate_fragment);
		tf.setTextExtracted(text);
	}


}