package br.com.facility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.to.Atividade;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;

public class AtividadeListActivity extends ActionBarActivity implements OnItemClickListener {

	ListView lstView;
	List<Atividade> atividades = new ArrayList<Atividade>();
	String titulo;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);
        
        lstView = (ListView) findViewById(R.id.list);
        lstView.setOnItemClickListener(this);
        
        Bundle bundle = getIntent().getExtras();
        titulo = bundle.getString("titulo");
        int categoria = bundle.getInt("categoria");
        
        ActionBar ab = getSupportActionBar();
        ab.setTitle(titulo);
        
        buscaAtividades(categoria);
        
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
	
	
	public void buscaAtividades(long categoria){
		//envia requisição para listar Atividades
        AQuery aq = new AQuery(AtividadeListActivity.this);
		aq.ajax(MainActivity.URL_BASE+"atividade/listar/"+categoria, String.class, new AjaxCallback<String>(){
			public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
				if(object != null){
					//recupera lista de json
					Atividade[] array = new Gson().fromJson(object, Atividade[].class);
					atividades = Arrays.asList(array);
					if(atividades.size() > 0){
						// seta lista na tela
						//seta lista na tela
						lstView.setAdapter(new AtividadeAdapter());
					}else{
						List<String> list = new ArrayList<String>();
						list.add("Nenhum serviço encontrado");
						// seta lista na tela
						ArrayAdapter<String> adapter = new ArrayAdapter<String>(
								AtividadeListActivity.this, android.R.layout.simple_list_item_1,
								list);
						lstView.setAdapter(adapter);
					}
					
				}else{
					Toast.makeText(AtividadeListActivity.this, "Erro ao buscar atividades", Toast.LENGTH_SHORT).show();
				}
			};
		});
	}
	
	private class AtividadeAdapter extends BaseAdapter{

		LayoutInflater inflater;
		
		public AtividadeAdapter() {
			inflater = (LayoutInflater) AtividadeListActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// envia para activity de especialidade
		Intent intent = new Intent(this,EspecialidadeActivity.class);
		intent.putExtra("atividade", arg0.getItemIdAtPosition(arg2));
		intent.putExtra("titulo", ((Atividade)arg0.getItemAtPosition(arg2)).getNome());
		startActivity(intent);
	}
	
}
