package br.com.facility;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.to.Negociacao;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


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
					lista = new Gson().fromJson(object, new TypeToken<List<Negociacao>>(){}.getType());
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

		public ListaNegociacaoAdapter() {
			//recupera layout e lista
			super(MainActivity.this, R.layout.item_negociacao, lista);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			Negociacao n = lista.get(position);
			
			//seta view para cada item da lista
			TextView txtTituloNegociacao = (TextView) findViewById(R.id.txtTituloNegociacao);
			TextView txtSubtituloNegociacao = (TextView) findViewById(R.id.txtSubtituloNegociacao);
			txtTituloNegociacao.setText("Titulo Negociacao");
			txtSubtituloNegociacao.setText("Subtitulo Negociacao");
			
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
