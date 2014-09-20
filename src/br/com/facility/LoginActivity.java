package br.com.facility;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

	private SharedPreferences pref;
	private Editor editor;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences pref = getApplicationContext().getSharedPreferences("FacilityPref", 0);
		editor = pref.edit();
		String user = pref.getString("user", null);
		
		if (user == null) {
			setContentView(R.layout.activity_login);
		}else{
			//envia para página principal
			Intent intent=  new Intent(LoginActivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
			Usuario u = new Gson().fromJson(user, Usuario.class);
			intent.putExtra("id", u.getId());
			
			startActivity(intent);
			finish();
		}
		
		setContentView(R.layout.activity_login);
		
		TextView lnkCadastrar = (TextView) findViewById(R.id.lnkCadastrar);
		lnkCadastrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// envia para página de cadastro
				Intent i = new Intent(LoginActivity.this,UsuarioCadastro.class);
				startActivity(i);
			}
		});
		
		TextView lnkPular = (TextView) findViewById(R.id.lnkPular);
		lnkPular.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// envia para página principal
				Intent i = new Intent(LoginActivity.this,MainActivity.class);
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
					//envia para página principal
					Intent intent=  new Intent(LoginActivity.this, MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					
					Usuario u = new Gson().fromJson(object, Usuario.class);
					intent.putExtra("id", u.getId());
					
					editor.putString("user", object);
					editor.commit();
					
					startActivity(intent);
					Toast.makeText(LoginActivity.this, "Logado!!", 8000).show();
					finish();
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

}
