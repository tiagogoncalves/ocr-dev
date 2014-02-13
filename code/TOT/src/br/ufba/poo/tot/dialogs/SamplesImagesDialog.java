package br.ufba.poo.tot.dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.camera.BitmapManager;
import br.ufba.poo.tot.fragments.CameraFragment;

/**
 * Classe que implementa um Dialog(espécie de janela pop-up) das imagens exemplos
 * @author Equipe OCRDev
 *
 */
public class SamplesImagesDialog extends DialogFragment {

	private CameraFragment cameraFragment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_samples_image, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        cameraFragment = (CameraFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.camera_fragment);
        loadComponents(v);
        return v;
    }

	private void loadComponents(View v) {
		ImageView tot1 = (ImageView)v.findViewById(R.id.tot_01);
		tot1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cameraFragment.setPhotoCaptured(inflateBitmap(1));
				SamplesImagesDialog.this.dismiss();
			}
		});
		
		ImageView tot2 = (ImageView)v.findViewById(R.id.tot_02);
		tot2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cameraFragment.setPhotoCaptured(inflateBitmap(2));
				SamplesImagesDialog.this.dismiss();
			}
		});
		
		ImageView tot3 = (ImageView)v.findViewById(R.id.tot_03);
		tot3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cameraFragment.setPhotoCaptured(inflateBitmap(3));
				SamplesImagesDialog.this.dismiss();
			}
		});
		
		ImageView tot4 = (ImageView)v.findViewById(R.id.tot_04);
		tot4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cameraFragment.setPhotoCaptured(inflateBitmap(4));
				SamplesImagesDialog.this.dismiss();
			}
		});
	}
	
	private Bitmap inflateBitmap(int id) {
		InputStream stream = null;
		Bitmap photo = null;
		BitmapManager bm = new BitmapManager();
		File f = new File(bm.getMediaStorageDir().getPath() + File.separator +"tot_0"+id+".png");
		Uri fileUri = Uri.fromFile(f);
		try {
			stream = getActivity().getContentResolver().openInputStream(fileUri);
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inPreferredConfig = Config.ARGB_8888;
			photo = BitmapFactory.decodeStream(stream, null, opts);
		} catch (FileNotFoundException e) {
			Log.e(SamplesImagesDialog.class.getName(),e.getMessage());
		}
		return photo;
	} 
    
}