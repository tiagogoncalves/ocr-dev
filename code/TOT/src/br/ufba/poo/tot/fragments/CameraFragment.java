package br.ufba.poo.tot.fragments;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.ufba.poo.tot.camera.CameraPreview;
import br.ufba.poo.tot.camera.CustomCamera;
import br.ufba.poo.tot.exceptions.UnavailableCameraException;

/**
 * Fragment responsável pela Câmera do aplicativo.
 * @author Equipe OCRDev	
 *
 */
public class CameraFragment extends Fragment {

    private CameraPreview preview;
    Camera camera;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
			camera = CustomCamera.getCameraInstance(this.getActivity());
	        preview = new CameraPreview(this.getActivity(),camera);
		} catch (UnavailableCameraException e) {
			e.printStackTrace();
		}


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return preview;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
			camera = CustomCamera.getCameraInstance(this.getActivity());
		} catch (UnavailableCameraException e) {
			e.printStackTrace();
		}
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
