package aaa.cubeup.cube;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import aaa.cubeup.cube.R.id;
import aaa.cubeup.cube.R.layout;
import aaa.cubeup.cube.ui.dashboardfrags.DashboardFrag;
import aaa.cubeup.cube.ui.viewmodels.ZZZZVM;


public class MainFrag extends AppCompatActivity {

	public final static boolean rebuildDB = true;
	public static long start;
	protected ZZZZVM cubeVM;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		start = System.nanoTime();
		getDelegate().setContentView(layout.frag_main);

		/* DELETE database */
		if (rebuildDB) getApplicationContext().deleteDatabase("catalog_database");

		cubeVM = new ViewModelProvider(this).get(ZZZZVM.class);

		showDashboard();
	}



	void showDashboard() {
		getSupportFragmentManager().beginTransaction().replace(id.fragment_container_main, new DashboardFrag(), DashboardFrag.TAG).commit();
	}

}
