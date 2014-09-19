package br.com.facility;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.facility.to.Usuario;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

public class UsuarioCadastro extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usuario_cadastro);
	}
	
	public void salvar(View v){
		
		EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
		EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
		EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
		Usuario usuario = new Usuario();
		usuario.setUsername(txtUsername.getText().toString());
		usuario.setEmail(txtEmail.getText().toString());
		usuario.setSenha(txtSenha.getText().toString());
		String usuarioJSON = new Gson().toJson(usuario);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AQuery.POST_ENTITY, usuarioJSON);
		
		AjaxCallback<String> cb = new AjaxCallback<String>(){
			@Override
			public void callback(String url, String object, AjaxStatus status) {
				if(object != null){
					Intent intent=  new Intent(UsuarioCadastro.this, MainActivity.class);
					startActivity(intent);
					Toast.makeText(UsuarioCadastro.this, "Logado!!", 8000).show();
					//TextView txtMsg = (TextView) findViewById(R.id.txtMsg);
					//txtMsg.setText("Usuário cadastrado!");
				}else{
					Toast.makeText(UsuarioCadastro.this, "Erro ao cadastrar!", 8000).show();
					//TextView txtMsg = (TextView) findViewById(R.id.txtMsg);
					//txtMsg.setText("Erro ao cadastrar!");
				}
			}
		};
		
		cb.header("Content-Type", "application/json");
		
		AQuery aq = new AQuery(this);
		aq.ajax(MainActivity.URL_BASE+"usuario/cadastrar", params, String.class, cb);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usuario_cadastro, menu);
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
