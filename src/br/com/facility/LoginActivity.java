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
import android.widget.TextView;
import android.widget.Toast;
import br.com.facility.config.Security_Util;
import br.com.facility.to.Usuario;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		TextView lnkCadastrar = (TextView) findViewById(R.id.lnkCadastrar);
		lnkCadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(LoginActivity.this,UsuarioCadastro.class);
				startActivity(i);
			}
		});
	}
	
	public void logar(View v){
		
		EditText txtLogin = (EditText) findViewById(R.id.txtLogin);
		EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
		String login = txtLogin.getText().toString();
		String senha = txtSenha.getText().toString();
		
		//criptografa login e senha
		try {
			login = Security_Util.encrypt(txtLogin.getText().toString());
			senha = Security_Util.encrypt(txtSenha.getText().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//instancia Usuario
		Usuario u = new Usuario();
		u.setUsername(login);
		u.setSenha(senha);
		String usuarioJSON = new Gson().toJson(u);
		
		//seta parametros url post
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AQuery.POST_ENTITY, usuarioJSON);
		
		//cria callback
		AjaxCallback<String> cb = new AjaxCallback<String>(){
			@Override
			public void callback(String url, String object, AjaxStatus status) {
				//valida retorno
				if(object != null){
					Intent intent=  new Intent(LoginActivity.this, MainActivity.class);
					
					Usuario u = new Gson().fromJson(object, Usuario.class);
					intent.putExtra("id", u.getId());
					startActivity(intent);
					Toast.makeText(LoginActivity.this, "Logado!!", 8000).show();
				}else{
					Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos!!", 8000).show();
				}
			}
		};
		
		cb.header("Content-Type", "application/json");
		
		//envia requisição para o servidor
		AQuery aq = new AQuery(this);
		aq.ajax(MainActivity.URL_BASE+"usuario/login", params, String.class, cb);
		
	}
	
	public void cadastrar(View v){
		Intent i = new Intent(this,UsuarioCadastro.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
