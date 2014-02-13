package br.ufba.poo.tot.camera.translaguage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.activities.TOTMainActivity;
import br.ufba.poo.tot.camera.BitmapManager;


public class CameraPreview extends FragmentActivity implements SensorEventListener {
	private Preview mPreview; 
	private ImageView mTakePicture;
	private TouchView mView;
	private PopupWindow mFromPopUpWindow;
	private PopupWindow mToPopUpWindow;

	private boolean mAutoFocus = true;

	private boolean mFlashBoolean = false;

	private SensorManager mSensorManager;
	private Sensor mAccel;
	private boolean mInitialized = false;
	private float mLastX = 0;
	private float mLastY = 0;
	private float mLastZ = 0;
	private Rect rec = new Rect();


	private int mScreenHeight;
	private int mScreenWidth;

	private boolean mInvalidate = false;

	private AlertDialog alert;

	private Intent intent;

	private ProgressDialog dialog;
	private boolean mNewDownload = false;
	private int mLanguageToDownload = 6;
	private int progress = 0;
	
	private LayoutParams lp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main); 

		//TODO Acelerômetro
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		mScreenHeight = displaymetrics.heightPixels;
		mScreenWidth = displaymetrics.widthPixels;
		Drawable mButtonDrawable = this.getResources().getDrawable(R.drawable.camera);

		//to hold my DownloadService intent - TODO Aqui é onde ele faz o download da base de comparação
		intent = new Intent(this,DownloadService.class);


		mTakePicture = (ImageView) findViewById(R.id.startcamerapreview);

		lp = new LayoutParams(mTakePicture.getLayoutParams());
		lp.setMargins((int)((double)mScreenWidth*.85),
				(int)((double)mScreenHeight*.70) ,
				(int)((double)mScreenWidth*.85)+mButtonDrawable.getMinimumWidth(), 
				(int)((double)mScreenHeight*.70)+mButtonDrawable.getMinimumHeight());
		mTakePicture.setLayoutParams(lp);
		rec.set((int)((double)mScreenWidth*.85),
				(int)((double)mScreenHeight*.10) ,
				(int)((double)mScreenWidth*.85)+mButtonDrawable.getMinimumWidth(), 
				(int)((double)mScreenHeight*.70)+mButtonDrawable.getMinimumHeight());
		mButtonDrawable = null;
		mTakePicture.setOnClickListener(previewListener);
		mPreview = (Preview) findViewById(R.id.preview);
		mView = (TouchView) findViewById(R.id.left_top_view);
		mView.setRec(rec);
		
		LayoutInflater inflater = (LayoutInflater)this.getSystemService
		(LAYOUT_INFLATER_SERVICE);
		GridView fromGridView = (GridView) inflater.inflate(R.layout.gridview, null,false);
		fromGridView.setAdapter(new FromImageAdapter(this));
		mFromPopUpWindow = new PopupWindow(fromGridView,mScreenWidth,mScreenHeight);
		fromGridView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Bundle b = new Bundle();
				b.putInt("lang", arg2);
				intent.putExtras(b);
				startService(intent);
				mFromPopUpWindow.dismiss();
				mTakePicture.setVisibility(View.VISIBLE);
				mView.setVisibility(View.VISIBLE);
				dialog = new ProgressDialog(CameraPreview.this);
				dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				dialog.setMessage("Loading language. The first time " +
						"this might take a few minutes while the language pack is" +
						" being downloaded. Chinese, Japanese and Korean take much longer and it is recommended" +
						" that for these languages Wifi is used. Please wait...");
				dialog.show();
				dialog.setCancelable(false);
				mNewDownload = true;
				mLanguageToDownload = arg2;
				Thread updateProgressDialog = new Thread(new Runnable(){
					public void run(){
						while (mNewDownload){
							try {
								Thread.sleep(500);
								dialog.setProgress(progress);
								File zipFile = new File(Environment.getExternalStorageDirectory(),"translanguage/tessdata/"+Languages.mLanguages[mLanguageToDownload]);
								progress = (int) ((zipFile.length()*100)/(Languages.mLanguageSizes[mLanguageToDownload]));
								File file = new File(Environment.getExternalStorageDirectory(),"translanguage/tessdata/"+Languages.mLanguages2[mLanguageToDownload]);
								if (file.exists()){
									long length = file.length();
									progress = (int) ((length*100)/Languages.mLanguageSizes[mLanguageToDownload])  + progress;
									if (length >= Languages.mLanguageSizes[mLanguageToDownload]){
										dialog.setProgress(100);
										dialog.dismiss();
										mNewDownload = false;
										progress = 0;
									}
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}
				});
				updateProgressDialog.start();
			}

		});

		GridView toGridView =  (GridView) inflater.inflate(R.layout.gridview, null, false);
		toGridView.setAdapter(new ToImageAdapter(this));
		mToPopUpWindow = new PopupWindow(toGridView,mScreenWidth,mScreenHeight);
		toGridView.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				mToPopUpWindow.dismiss();
				mTakePicture.setVisibility(View.VISIBLE);
				mView.setVisibility(View.VISIBLE);
			}

		});
		Bundle b = new Bundle();
		b.putInt("lang", 6);
		intent.putExtras(b);
		startService(intent);
	}

	private AutoFocusCallback myAutoFocusCallback = new AutoFocusCallback(){

		public void onAutoFocus(boolean autoFocusSuccess, Camera arg1) {
			mAutoFocus = true;
		}};

		public Double[] getRatio(){
			Size s = mPreview.getCameraParameters().getPreviewSize();
			double heightRatio = (double)s.height/(double)mScreenHeight;
			double widthRatio = (double)s.width/(double)mScreenWidth;
			Double[] ratio = {heightRatio,widthRatio};
			return ratio;
		}

		private OnClickListener flashListener = new OnClickListener(){

			@Override
			public void onClick(View v) {
				if (mFlashBoolean){
					mPreview.setFlash(false);
				}
				else{
					mPreview.setFlash(true);
				}
				mFlashBoolean = !mFlashBoolean;
			}

		};


		private OnClickListener previewListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mAutoFocus){
					mAutoFocus = false;
					mPreview.setCameraFocus(myAutoFocusCallback);
					Wait.oneSec();
					Thread tGetPic = new Thread( new Runnable() {
						public void run() {
							Double[] ratio = getRatio();
							int left = (int) (ratio[1]*(double)mView.getmLeftTopPosX());
							int top = (int) (ratio[0]*(double)mView.getmLeftTopPosY());

							int right = (int)(ratio[1]*(double)mView.getmRightBottomPosX());

							int bottom = (int)(ratio[0]*(double)mView.getmRightBottomPosY());
							savePhoto(mPreview.getPic(left,top,right,bottom));
							mAutoFocus = true;
						} 
					});
					tGetPic.start();
				}
				boolean pressed = false;
				if (!mTakePicture.isPressed()){
					pressed = true;
				}
			}	   
		};

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK){
				finish();
			}
			return super.onKeyDown(keyCode, event);
		}

		private boolean savePhoto(Bitmap bm) {
			System.gc();
			FileOutputStream image = null;
			try {
				BitmapManager bma = new BitmapManager();
				image = new FileOutputStream(bma.getMediaFile());
			} catch (FileNotFoundException e) {
				Log.e(CameraPreview.class.getName(),e.getMessage());
				return false;
			}
			bm.compress(CompressFormat.JPEG, 100, image);
			Intent returnIntent = new Intent();
			returnIntent.putExtra(TOTMainActivity.PHOTO,bm);
			setResult(RESULT_OK,returnIntent);     
			return true;
		}

		public boolean onInterceptTouchEvent(MotionEvent ev) {
			final int action = ev.getAction();
			boolean intercept = false;
			switch (action) {
			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_DOWN:
				float x = ev.getX();
				float y = ev.getY();

				if ((x >= rec.left) && (x <= rec.right) && (y>=rec.top) && (y<=rec.bottom)){
					intercept = true;
				}
				break;
			}
			return intercept;
		}

		public void onSensorChanged(SensorEvent event) {

			if (mInvalidate == true){
				mView.invalidate();
				mInvalidate = false;
			}
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			if (!mInitialized){
				mLastX = x;
				mLastY = y;
				mLastZ = z;
				mInitialized = true;
			}
			float deltaX  = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);

			if (deltaX > .5 && mAutoFocus){ 
				mAutoFocus = false;
				mPreview.setCameraFocus(myAutoFocusCallback);
			}
			if (deltaY > .5 && mAutoFocus){
				mAutoFocus = false;
				mPreview.setCameraFocus(myAutoFocusCallback);
			}
			if (deltaZ > .5 && mAutoFocus){ 
				mAutoFocus = false;
				mPreview.setCameraFocus(myAutoFocusCallback);
			}

			mLastX = x;
			mLastY = y;
			mLastZ = z;

		}

		@Override
		protected void onDestroy() {
			super.onDestroy();
		}

		@Override
		protected void onPause() {
			super.onPause();
			mSensorManager.unregisterListener(this);
		}

		@Override
		protected void onResume() {
			super.onResume();
			mSensorManager.registerListener(this, mAccel, SensorManager.SENSOR_DELAY_UI);
		}

		@Override
		protected void onRestart() {
			super.onRestart();
		}

		@Override
		protected void onStop() {
			super.onStop();
		}

		@Override
		protected void onStart() {
			super.onStart();
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
}