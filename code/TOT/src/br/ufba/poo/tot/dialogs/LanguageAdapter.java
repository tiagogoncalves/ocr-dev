package br.ufba.poo.tot.dialogs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.ufba.poo.tot.R;
import br.ufba.poo.tot.translator.TTranslator;

public class LanguageAdapter extends BaseAdapter{

	private List<String> languages;
	private Context context;
	
	
	public LanguageAdapter(Context context,Map<String,String> mapLanguage){
		this.context=context;
		setLanguages(new ArrayList<String>());
		for(Object l: mapLanguage.keySet().toArray())
			getLanguages().add((String)l);
	}
	
	
	@Override
	public int getCount() {
		return getLanguages().size();
	}

	@Override
	public Object getItem(int position) {
		return getLanguages().get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_language_list, null);
		TextView itemLanguage = (TextView) view.findViewById(R.id.item_language);
		itemLanguage.setText(TTranslator.getMapLanguage(context).get(getLanguages().get(position)));
		return view;
	}


	public List<String> getLanguages() {
		return languages;
	}


	public void setLanguages(List<String> languages) {
		this.languages = languages;
	}


}
