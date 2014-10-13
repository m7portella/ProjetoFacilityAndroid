package br.com.facility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.enums.TipoPessoa;
import br.com.facility.to.Negociacao;
import br.com.facility.to.Profissional;
import br.com.facility.to.Usuario;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

@SuppressLint("NewApi")
public class NegociacaoListFragment extends Fragment {

	List<Negociacao> lista = new ArrayList<Negociacao>();
	ListView lstNegociacao;

	@Override
	public View onCreateView(LayoutInflater inflater,
			final ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_negociacoes,
				container, false);
		lstNegociacao = (ListView) rootView.findViewById(R.id.lstNegociacao);
		lstNegociacao.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(),
						"Selecionou serviço " + arg0.getItemIdAtPosition(arg2),
						Toast.LENGTH_SHORT).show();
			}
		});

		// recupera id usuário
		SharedPreferences pref = getActivity().getSharedPreferences(
				"FacilityPref", 0);
		String user = pref.getString("user", null);
		int id = 0;
		if (user != null) {
			Usuario u = new Gson().fromJson(user, Usuario.class);
			id = u.getId();
		}

		// envia requisição para listar Negociações do Usuario
		AQuery aq = new AQuery(container);
		aq.ajax(MainActivity.URL_BASE + "negociacao/listar/" + id,
				String.class, new AjaxCallback<String>() {
					public void callback(String url, String object,
							com.androidquery.callback.AjaxStatus status) {
						if (object != null) {
							// recupera lista de json
							Negociacao[] array = new Gson().fromJson(object,
									Negociacao[].class);
							lista = Arrays.asList(array);
							
							//verifica se achou negociações
							if(lista.size() > 0){
								// seta lista na tela
								ArrayAdapter<Negociacao> adapter = new ListaNegociacaoAdapter();
								lstNegociacao.setAdapter(adapter);
							}else{
								List<String> list = new ArrayList<String>();
								list.add("Nenhuma negociação cadastrada");
								// seta lista na tela
								ArrayAdapter<String> adapter = new ArrayAdapter<String>(
										getActivity(), android.R.layout.simple_list_item_1,
										list);
								lstNegociacao.setAdapter(adapter);
							}
							
						} else {
							Toast.makeText(container.getContext(), "Erro ao buscar negociações", Toast.LENGTH_SHORT).show();
						}
					};
				});

		return rootView;
	}

	// Adapter para a lista de Negociacoes
	private class ListaNegociacaoAdapter extends ArrayAdapter<Negociacao> {

		private LayoutInflater inflater;

		public ListaNegociacaoAdapter() {
			// recupera layout e lista
			super(getActivity(), R.layout.item_negociacao, lista);
			inflater = (LayoutInflater) getActivity().getSystemService(
					Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			// recupera item
			Negociacao n = lista.get(position);

			// infla view
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.item_negociacao,
						parent, false);
			}

			// seta view para cada item da lista
			TextView txtTituloNegociacao = (TextView) convertView
					.findViewById(R.id.txtTituloNegociacao);
			TextView txtProtocoloNegociacao = (TextView) convertView
					.findViewById(R.id.txtProtocoloNegociacao);
			TextView txtSubtituloNegociacao = (TextView) convertView
					.findViewById(R.id.txtSubtituloNegociacao);

			Profissional p = n.getProfissional();
			if (p.getTipo() == TipoPessoa.FISICA) {
				txtTituloNegociacao
						.setText(n.getProfissional().getClienteFisico()
								.getNome()
								+ " "
								+ n.getProfissional().getClienteFisico()
										.getSobrenome());
			} else if (p.getTipo() == TipoPessoa.JURIDICA) {
				txtTituloNegociacao.setText(n.getProfissional()
						.getClienteJuridico().getNomeFantasia());
			}
			txtSubtituloNegociacao.setText("Projeto: "
					+ n.getProjeto().getDescricao());
			txtProtocoloNegociacao.setText("Protocolo: " + n.getProtocolo());

			return convertView;
		}

		@Override
		public long getItemId(int position) {
			Negociacao n = lista.get(position);
			return n.getProtocolo();
		}

	}

}
