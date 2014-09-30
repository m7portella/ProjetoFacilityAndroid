package br.com.facility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.to.Atividade;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

public class AtividadeListFragment extends Fragment {

	ListView lstServicos;
	List<Atividade> atividades = new ArrayList<Atividade>();
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_servicos, container, false);
		lstServicos = (ListView) rootView.findViewById(R.id.lstServicos);
		
		//TODO passar id de categoria da outra tela
		
		Intent intent = getActivity().getIntent();
		Bundle bundle = intent.getExtras();
		int categoria = bundle.getInt("categoria");
		
		buscaAtividades(categoria);
		
		return rootView;
	}	
	
	public void buscaAtividades(long categoria){
		//envia requisição para listar Atividades
        AQuery aq = new AQuery(getView());
		aq.ajax(MainActivity.URL_BASE+"atividade/listar/"+categoria, String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					Atividade[] array = new Gson().fromJson(object, Atividade[].class);
					atividades = Arrays.asList(array);
					//seta lista na tela
					lstServicos.setAdapter(new AtividadeListAdapter());
				}else{
					Toast.makeText(getView().getContext(), "Erro ao buscar atividades", Toast.LENGTH_SHORT).show();
				}
			};
		});
	}
	
	private class AtividadeListAdapter extends BaseAdapter{

		LayoutInflater inflater;
		
		public AtividadeListAdapter() {
			inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public int getCount() {
			return atividades.size();
		}

		@Override
		public Object getItem(int position) {
			Atividade a = atividades.get(position);
			return a;
		}

		@Override
		public long getItemId(int position) {
			Atividade a = atividades.get(position);
			return a.getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Atividade a = atividades.get(position);
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item_servico, parent, false);
			}
			TextView txtCategoria = (TextView) convertView.findViewById(R.id.txtCategoria);
			txtCategoria.setText(a.getNome());
			
			return convertView;
		}
	
	}
}
