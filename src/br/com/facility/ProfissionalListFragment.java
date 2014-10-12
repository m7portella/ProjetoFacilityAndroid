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
import br.com.facility.enums.TipoPessoa;
import br.com.facility.to.Categoria;
import br.com.facility.to.Profissional;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

public class ProfissionalListFragment extends Fragment {

	ListView lstServicos;
	List<Profissional> profissionais = new ArrayList<Profissional>();
	long atividade;
	
	public View onCreateView(LayoutInflater inflater,
			@Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_profissionais, container, false);
		lstServicos = (ListView) rootView.findViewById(R.id.lstProfissionais);
		lstServicos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getActivity(), "Selecionoi o profissional "+arg3, Toast.LENGTH_SHORT);
				/*Categoria c = (Categoria)arg0.getItemAtPosition(arg2);
				Intent i = new Intent(getActivity(), AtividadeListActivity.class);
				i.putExtra("categoria", c.getId());
				i.putExtra("titulo", c.getNome());
				startActivity(i);*/
			}
		});
		
		Bundle bundle = getActivity().getIntent().getExtras();
		atividade = bundle.getLong("atividade");
		
		buscaProfissionais(container);
		
		return rootView;
	}
	
	private void buscaProfissionais(final ViewGroup container) {
		//envia requisição para listar Categorias
        AQuery aq = new AQuery(container);
		aq.ajax(MainActivity.URL_BASE+"profissional/poratividade/"+atividade, String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					Profissional[] array = new Gson().fromJson(object, Profissional[].class);
					profissionais = Arrays.asList(array);
					//seta lista na tela
					lstServicos.setAdapter(new CategoriaListAdapter());
				}else{
					Toast.makeText(container.getContext(), "Erro ao buscar profissionais", Toast.LENGTH_SHORT).show();
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
			
			Profissional p = profissionais.get(position);
			
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item_negociacao, parent, false);
			}
			
			TextView txtTitulo = (TextView) convertView.findViewById(R.id.txtTituloNegociacao);
			TextView txtTipoPessoa = (TextView) convertView.findViewById(R.id.txtProtocoloNegociacao);
			txtTitulo.setText(p.getNome());
			if(p.getTipo()==TipoPessoa.FISICA){
				txtTipoPessoa.setText("Pessoa Física");
			}else{
				txtTipoPessoa.setText("Empresa");
			}
			
			
			return convertView;
		}

		@Override
		public int getCount() {
			return profissionais.size();
		}

		@Override
		public Object getItem(int position) {
			Profissional p = profissionais.get(position);
			return p;
		}

		@Override
		public long getItemId(int position) {
			Profissional p = profissionais.get(position);
			return p.getId();
		}
		
	}
}
