package br.com.facility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.enums.TipoPessoa;
import br.com.facility.enums.TipoUsuario;
import br.com.facility.to.Negociacao;
import br.com.facility.to.Usuario;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

public class NegociacaoProfissionalListActivity extends ActionBarActivity {

	List<Negociacao> lista = new ArrayList<Negociacao>();
	ListView lstNegociacao;
	Usuario u;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_negociacao_profissional_list);
		
		lstNegociacao = (ListView) findViewById(R.id.lstNegociacaoProfissional);
		lstNegociacao.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(NegociacaoProfissionalListActivity.this, "Selecionou serviço "+arg0.getItemIdAtPosition(arg2), Toast.LENGTH_SHORT).show();
			}
		});
		
		//recupera id usuário
		SharedPreferences pref = getSharedPreferences("FacilityPref", 0);
		String user = pref.getString("user", null);
		int id = 0;
		if (user != null) {
			u = new Gson().fromJson(user, Usuario.class);
			id = u.getId();
		}
		
		//envia requisição para listar Negociações do Usuario
        AQuery aq = new AQuery(this);
		aq.ajax(MainActivity.URL_BASE+"negociacao/listarporprofissional/"+id, String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					Negociacao[] array = new Gson().fromJson(object, Negociacao[].class);
					lista = Arrays.asList(array);
					//seta lista na tela
					ArrayAdapter<Negociacao> adapter = new ListaNegociacaoProfissionalAdapter();
					lstNegociacao.setAdapter(adapter);
				}else{
					Toast.makeText(NegociacaoProfissionalListActivity.this, "Erro ao buscar negociações", Toast.LENGTH_SHORT).show();
				}
			};
		});
	}
	
	//Adapter para a lista de Negociacoes
    private class ListaNegociacaoProfissionalAdapter extends ArrayAdapter<Negociacao>{
    	
    	private LayoutInflater inflater;
    	
		public ListaNegociacaoProfissionalAdapter() {
			//recupera layout e lista
			super(NegociacaoProfissionalListActivity.this, R.layout.item_negociacao, lista);
			inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			//recupera item
			Negociacao n = lista.get(position);
			
			//infla view
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item_negociacao, parent, false);
			}
			
			//seta view para cada item da lista
			TextView txtTituloNegociacao = (TextView) convertView.findViewById(R.id.txtTituloNegociacao);
			TextView txtProtocoloNegociacao = (TextView) convertView.findViewById(R.id.txtProtocoloNegociacao);
			TextView txtSubtituloNegociacao = (TextView) convertView.findViewById(R.id.txtSubtituloNegociacao);
			
			// teste - pega nome do usuário
			txtTituloNegociacao.setText(n.getUsuario().getNome());
			
			txtSubtituloNegociacao.setText("Projeto: "+n.getProjeto().getDescricao());
			txtProtocoloNegociacao.setText("Protocolo: "+n.getProtocolo());
			
			return convertView;
		}
		
		@Override
		public long getItemId(int position) {
			Negociacao n = lista.get(position);
			return n.getProtocolo();
		}
    	
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.negociacao_profissional_list, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.logout) {
        	
        	//desloga sessao
        	SharedPreferences pref = getApplicationContext().getSharedPreferences("FacilityPref", 0);
        	Editor editor = pref.edit();
        	editor.remove("user");
        	editor.commit();
        	
        	//envia para tela de login
        	Intent intent = new Intent(this, LoginActivity.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
            return true;
        }else if(id == R.id.toggle_cliente){
        	Intent intent = new Intent(this, MainActivity.class);
        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
	
}
