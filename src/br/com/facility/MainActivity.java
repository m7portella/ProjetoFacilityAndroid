package br.com.facility;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.google.gson.Gson;

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
import br.com.facility.enums.TipoUsuario;
import br.com.facility.to.Negociacao;
import br.com.facility.to.Usuario;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	// note indra 4g
	public static String URL_BASE = "http://192.168.43.42:8080/ProjetoFacility/rest/";

	// note indra em casa
	// public static String URL_BASE =
	// "http://192.168.1.8:8080/ProjetoFacility/rest/";

	// mac 4g
	// public static String URL_BASE =
	// "http://192.168.43.136:8080/ProjetoFacility/rest/";

	// mac em casa
	// public static String URL_BASE =
	// "http://192.168.1.2:8080/ProjetoFacility/rest/";

	ListView lstNegociacao;
	List<Negociacao> lista = new ArrayList<Negociacao>();
	TabAdapter tabAdapter;
	ViewPager viewPager;
	Usuario u;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// final ActionBar actionBar = getSupportActionBar();
		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabAdapter = new TabAdapter(getSupportFragmentManager());

		viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
		viewPager.setAdapter(tabAdapter);

		// seta uma tab para cada seção
		/*
		 * for (int i = 0; i < tabAdapter.getCount(); i++) {
		 * actionBar.addTab(actionBar
		 * .newTab().setText(tabAdapter.getPageTitle(i)).setTabListener(this));
		 * }
		 * 
		 * viewPager.setOnPageChangeListener(new
		 * ViewPager.SimpleOnPageChangeListener(){
		 * 
		 * @Override public void onPageSelected(int position) {
		 * actionBar.setSelectedNavigationItem(position); } });
		 */

		// recupera usuário
		SharedPreferences pref = getSharedPreferences("FacilityPref", 0);
		String user = pref.getString("user", null);
		if (user != null) {
			u = new Gson().fromJson(user, Usuario.class);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		if (u.getTipo() == TipoUsuario.PROFISSIONAL) {
			getMenuInflater().inflate(R.menu.main_profissional, menu);
		} else {
			getMenuInflater().inflate(R.menu.main, menu);
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {

			// desloga sessao
			SharedPreferences pref = getApplicationContext()
					.getSharedPreferences("FacilityPref", 0);
			Editor editor = pref.edit();
			editor.remove("user");
			editor.commit();

			// envia para tela de login
			Intent intent = new Intent(this, LoginActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			finish();
			return true;
		} else if (id == R.id.toggle_profissional) {
			Intent intent = new Intent(this,
					NegociacaoProfissionalListActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}

	public class TabAdapter extends FragmentPagerAdapter {

		public TabAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			switch (arg0) {
			case 0:
				return new ServicoListFragment();// fragment categorias
													// newInstance
			case 1:
				return new NegociacaoListFragment(); // fragment negociações
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
			switch (position) {
			case 0:
				return "Serviços".toUpperCase(l);
			case 1:
				return "Negociações".toUpperCase(l);
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
