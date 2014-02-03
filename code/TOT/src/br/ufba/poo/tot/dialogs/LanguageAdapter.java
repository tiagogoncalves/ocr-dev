package br.ufba.poo.tot.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.pojo.OCRFile;

public class LanguageAdapter extends BaseAdapter{

	private List<String> languages;
	private Context context;
	
	
	public LanguageAdapter(Context context,Map<String,String> mapLanguage){
		this.context=context;
		languages = new ArrayList<String>();
		for(Object l: mapLanguage.keySet().toArray())
			languages.add((String)l);
		Collections.sort(languages);//TODO Não está ordenando
	}
	
	
	@Override
	public int getCount() {
		return languages.size();
	}

	@Override
	public Object getItem(int position) {
		return languages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_language_list, null);
		RadioButton itemLanguage = (RadioButton) view.findViewById(R.id.item_language);
		itemLanguage.setText(OCRFile.getMapLanguage(context).get(languages.get(position)));
		return view;
	}


}
