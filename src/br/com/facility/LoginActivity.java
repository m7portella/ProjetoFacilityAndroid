package br.com.facility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}
	
	public void logar(View v){
		EditText txtLogin = (EditText) findViewById(R.id.txtLogin);
		EditText txtSenha = (EditText) findViewById(R.id.txtSenha);
		String login = txtLogin.getText().toString();
		String senha = txtSenha.getText().toString();
		AQuery aq = new AQuery(this);
		aq.ajax(MainActivity.URL_BASE+"usuario/login/"+login+"/"+senha, String.class, new AjaxCallback<String>(){
			@Override
			public void callback(String url, String object, AjaxStatus status) {
				if (object != null) {
					Intent intent=  new Intent(/*HomeActivity*/);
					Toast.makeText(LoginActivity.this, "Logado!!", 8000).show();
				}else{
					Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos!!", 8000).show();
				}
			}
		});
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
