package br.ufba.poo.tot.camera.translaguage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import br.ufba.poo.tot.R;

public class FromImageAdapter  extends BaseAdapter{

	private Context mContext;

	public FromImageAdapter(Context c) {
		mContext = c;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return mLanguages.length;
	}

	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {  // if it's not recycled, initialize some attributes
			imageView = new ImageView(mContext);
			imageView.setLayoutParams(new GridView.LayoutParams(100, 50));
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			//imageView.setPadding(8, 8, 8, 8);
		} else {
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mLanguages[position]);
		return imageView;
	}
	
	 // references to our images
    private Integer[] mLanguages = {
          R.drawable.bulgarian,
          R.drawable.chinese_mainland,
          R.drawable.chinese_taiwan,
          R.drawable.czech,
          R.drawable.danish,
          R.drawable.dutch,
          R.drawable.english,
          R.drawable.finnish,
          R.drawable.french,
          R.drawable.german,
          R.drawable.greek,
          R.drawable.hungarian,
          R.drawable.indonesian,
          R.drawable.italian,
          R.drawable.japanese,
          R.drawable.korean,
          R.drawable.latvian,
          R.drawable.lithuanian,
          R.drawable.norwegian,
          R.drawable.polish,
          R.drawable.portuguese,
          R.drawable.romanian,
          R.drawable.russian,
          R.drawable.slovak,
          R.drawable.slovanian,
          R.drawable.spanish,
          R.drawable.swedish,
          R.drawable.turkish,
          R.drawable.ukranian,
          R.drawable.vietnamese
          
    };
}
