package br.ufba.poo.tot.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.camera.CameraCapturer;
import br.ufba.poo.tot.camera.events.OnCameraListener;

/**
 * Esta Fragment é corresponde a Barra Superior Principal do Aplicativo.
 * @author OCRDev
 *
 */
public class CameraFragment extends Fragment  implements OnCameraListener{
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private Uri fileUri;
	private Bitmap photo;
	TextView initial;
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
		initial = (TextView) view .findViewById(R.id.initial);
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
	}


}