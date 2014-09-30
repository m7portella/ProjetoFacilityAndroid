package br.com.facility;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
		
		//instancia Usuario
		EditText txtUsername = (EditText) findViewById(R.id.txtUsername);
		EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
		EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
		Usuario usuario = new Usuario();
		usuario.setUsername(txtUsername.getText().toString());
		usuario.setEmail(txtEmail.getText().toString());
		usuario.setSenha(txtSenha.getText().toString());
		String usuarioJSON = new Gson().toJson(usuario);
		
		//seta parâmetros na url POST 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(AQuery.POST_ENTITY, usuarioJSON);
		
		//envia requisição para salvar Usuario
		AjaxCallback<String> cb = new AjaxCallback<String>(){
			@Override
			public void callback(String url, String object, AjaxStatus status) {
				//valida retorno da requisição
				if(object != null){
					//Envia para tela principal
					Intent intent=  new Intent(UsuarioCadastro.this, MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					
					startActivity(intent);
					Toast.makeText(UsuarioCadastro.this, "Usuário cadastrado!", Toast.LENGTH_SHORT).show();
					finish();
					
				}else{
					Toast.makeText(UsuarioCadastro.this, "Erro ao cadastrar!", Toast.LENGTH_SHORT).show();
				}
			}
		};
		
		cb.header("Content-Type", "application/json");
		
		AQuery aq = new AQuery(this);
		aq.ajax(MainActivity.URL_BASE+"usuario/cadastrar", params, String.class, cb);
	}

}
