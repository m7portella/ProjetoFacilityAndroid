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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.to.Atividade;
import br.com.facility.to.Categoria;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

public class ServicoListFragment extends Fragment {

	ListView lstServicos;
	List<Categoria> categorias = new ArrayList<Categoria>();
	//List<Atividade> atividades = new ArrayList<Atividade>();
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_servicos, container, false);
		lstServicos = (ListView) rootView.findViewById(R.id.lstServicos);
		lstServicos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Categoria c = (Categoria)arg0.getItemAtPosition(arg2);
				Intent i = new Intent(getActivity(), AtividadeListActivity.class);
				i.putExtra("categoria", c.getId());
				i.putExtra("titulo", c.getNome());
				startActivity(i);
			}
		});
		
		buscaCategorias(container);
		
		return rootView;
	}

	private void buscaCategorias(final ViewGroup container) {
		//envia requisição para listar Categorias
        AQuery aq = new AQuery(container);
		aq.ajax(MainActivity.URL_BASE+"atividade/categorias", String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					Categoria[] array = new Gson().fromJson(object, Categoria[].class);
					categorias = Arrays.asList(array);
					//seta lista na tela
					lstServicos.setAdapter(new CategoriaListAdapter());
				}else{
					Toast.makeText(container.getContext(), "Erro ao buscar categorias", Toast.LENGTH_SHORT).show();
				}
			};
		});
	}
	
	private class CategoriaListAdapter extends BaseAdapter{

		LayoutInflater inflater;
		
		public CategoriaListAdapter() {
			inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			Categoria c = categorias.get(position);
			
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item_servico, parent, false);
			}
			
			TextView txtCategoria = (TextView) convertView.findViewById(R.id.txtCategoria);
			txtCategoria.setText(c.getNome());
			
			return convertView;
		}

		@Override
		public int getCount() {
			return categorias.size();
		}

		@Override
		public Object getItem(int position) {
			Categoria c = categorias.get(position);
			return c;
		}

		@Override
		public long getItemId(int position) {
			Categoria c = categorias.get(position);
			return c.getId();
		}
		
	}

		
}

