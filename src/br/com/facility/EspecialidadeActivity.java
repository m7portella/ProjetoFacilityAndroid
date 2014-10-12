package br.com.facility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import br.com.facility.to.Negociacao;

public class EspecialidadeActivity extends ActionBarActivity implements ActionBar.TabListener{

	ListView lstNegociacao;
	List<Negociacao> lista = new ArrayList<Negociacao>();
	TabAdapter tabAdapter;
	ViewPager viewPager;
	EspecialidadeListFragment atvFragment;
	String titulo;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Bundle bundle = getIntent().getExtras();
        titulo = bundle.getString("titulo");
        
        ActionBar ab = getSupportActionBar();
        ab.setTitle(titulo);
        
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        
        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(tabAdapter);
       
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
    
    public class TabAdapter extends FragmentPagerAdapter{

		public TabAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			switch (arg0){
			case 0:
				return new EspecialidadeListFragment();				
			case 1:
				return new ProfissionalListFragment();
		}
		return null;
		}

		@Override
		public int getCount() {
			return 2;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position){
				case 0:
					return "Especialidades".toUpperCase(l);
				case 1:
					return "Profissionais".toUpperCase(l);
			}
			return null;
		}
    	
    }

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		viewPager.setCurrentItem(arg0.getPosition());		
	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
	}
	
}
