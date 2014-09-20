package br.com.facility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.enums.TipoPessoa;
import br.com.facility.to.Negociacao;
import br.com.facility.to.Profissional;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity {
	
	//note indra 4g
	//public static String URL_BASE = "http://192.168.43.42:8080/ProjetoFacility/rest/";
	
	//note indra em casa
	public static String URL_BASE = "http://192.168.1.10:8080/ProjetoFacility/rest/";
	
	//mac 4g
	//public static String URL_BASE = "http://192.168.43.136:8080/ProjetoFacility/rest/";
	
	//mac em casa
	//public static String URL_BASE = "http://192.168.1.2:8080/ProjetoFacility/rest/";
	
	ListView lstNegociacao;
	List<Negociacao> lista = new ArrayList<Negociacao>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstNegociacao = (ListView) findViewById(R.id.lstNegociacao);
        
        //recupera id do Usuario
        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);
        
        //envia requisição para listar Negociações do Usuario
        AQuery aq = new AQuery(this);
		aq.ajax(MainActivity.URL_BASE+"negociacao/listar/"+id, String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					//Type listType = new TypeToken<ArrayList<Negociacao>>() {}.getType();
					//lista = new Gson().fromJson(object, listType);
					Negociacao[] array = new Gson().fromJson(object, Negociacao[].class);
					lista = Arrays.asList(array);
					//seta lista na tela
					ArrayAdapter<Negociacao> adapter = new ListaNegociacaoAdapter();
					lstNegociacao.setAdapter(adapter);
				}else{
					Toast.makeText(MainActivity.this, "Erro ao buscar negociações",	8000).show();
				}
			};
		});
    }
    
    //Adapter para a lista de Negociacoes
    private class ListaNegociacaoAdapter extends ArrayAdapter<Negociacao>{
    	
    	private LayoutInflater inflater;
    	private Activity a;
    	
		public ListaNegociacaoAdapter() {
			//recupera layout e lista
			super(MainActivity.this, R.layout.item_negociacao, lista);
			inflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
			TextView txtSubtituloNegociacao = (TextView) convertView.findViewById(R.id.txtSubtituloNegociacao);
			
			Profissional p = n.getProfissional();
			if (p.getTipo() == TipoPessoa.FISICA){
				txtTituloNegociacao.setText(n.getProfissional().getClienteFisico().getNome()+" "+n.getProfissional().getClienteFisico().getSobrenome());
			}else if (p.getTipo() == TipoPessoa.JURIDICA){
				txtTituloNegociacao.setText(n.getProfissional().getClienteJuridico().getNomeFantasia());
			}
			txtSubtituloNegociacao.setText("Projeto: "+n.getProjeto().getDescricao());
			
			return convertView;
		}
    	
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	
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
        }
        return super.onOptionsItemSelected(item);
    }
}
