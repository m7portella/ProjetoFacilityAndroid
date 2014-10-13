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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.to.Especialidade;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

public class EspecialidadeListFragment extends Fragment {

	ListView lstServicos;
	List<Especialidade> especialidades = new ArrayList<Especialidade>();
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_servicos, container, false);
		lstServicos = (ListView) rootView.findViewById(R.id.lstServicos);
		
		Intent intent = getActivity().getIntent();
		Bundle bundle = intent.getExtras();
		long atividade = bundle.getLong("atividade");
		
		buscaEspecialidades(atividade);
		
		return rootView;
	}	
	
	public void buscaEspecialidades(long atividade){
		//envia requisição para listar Atividades
        AQuery aq = new AQuery(getView());
		aq.ajax(MainActivity.URL_BASE+"atividade/especialidades/"+atividade, String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					Especialidade[] array = new Gson().fromJson(object, Especialidade[].class);
					especialidades = Arrays.asList(array);
					if(especialidades.size() > 0){
						//seta lista na tela
						lstServicos.setAdapter(new EspecialidadeListAdapter());
					}else{
						List<String> list = new ArrayList<String>();
						list.add("Nenhuma especialidade encontrada");
						// seta lista na tela
						ArrayAdapter<String> adapter = new ArrayAdapter<String>(
								getActivity(), android.R.layout.simple_list_item_1,
								list);
						lstServicos.setAdapter(adapter);
					}
					
				}else{
					Toast.makeText(getView().getContext(), "Erro ao buscar atividades", Toast.LENGTH_SHORT).show();
				}
			};
		});
	}
	
	private class EspecialidadeListAdapter extends BaseAdapter{

		LayoutInflater inflater;
		
		public EspecialidadeListAdapter() {
			inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public int getCount() {
			return especialidades.size();
		}

		@Override
		public Object getItem(int position) {
			Especialidade a = especialidades.get(position);
			return a;
		}

		@Override
		public long getItemId(int position) {
			Especialidade a = especialidades.get(position);
			return a.getId();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Especialidade a = especialidades.get(position);
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item_servico, parent, false);
			}
			TextView txtCategoria = (TextView) convertView.findViewById(R.id.txtCategoria);
			txtCategoria.setText(a.getNome());
			
			return convertView;
		}
	
	}
}
