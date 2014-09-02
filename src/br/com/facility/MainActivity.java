package br.com.facility;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.facility.to.Usuario;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.google.gson.Gson;


public class MainActivity extends ActionBarActivity {
	
	Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void buscar(View v){
    	EditText txtId = (EditText)findViewById(R.id.txtId);
    	int id = Integer.parseInt(txtId.getText().toString());
    	String url = "http://192.168.43.136:8080/ProjetoFacility/rest/usuario/buscar/";
    	AQuery aq = new AQuery(this);
    	aq.ajax(url+id, String.class, new AjaxCallback<String>(){
    		public void callback(String url, String object, com.androidquery.callback.AjaxStatus status) {
    			
    			u = new Gson().fromJson(object, Usuario.class);
    			TextView txtNome = (TextView) findViewById(R.id.txtUsername);
    			if (u != null) {
        			txtNome.setText(u.getUsername());
				}else{
					txtNome.setText("Usuário não encontrado");
				}
    			
    		};
    	});
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
