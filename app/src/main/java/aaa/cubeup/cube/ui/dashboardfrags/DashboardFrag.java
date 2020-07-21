package aaa.cubeup.cube.ui.dashboardfrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import aaa.cubeup.cube.MainFrag;
import aaa.cubeup.cube.R.id;
import aaa.cubeup.cube.R.layout;

public class DashboardFrag extends Fragment {

	public static final String TAG = "DashboardFrag";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(layout.frag_dashboard, container, false);
	}

	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}

	@Override
	public void onResume() {
		super.onResume();

		BottomNavigationView bottomNavView = ((MainFrag) requireContext()).findViewById(id.nav_view);
		bottomNavView.setItemIconTintList(null);
		bottomNavView.setItemTextColor(null);
		NavController navController = Navigation.findNavController(((MainFrag) requireContext()).findViewById(id.nav_host_center));
		NavigationUI.setupWithNavController(bottomNavView, navController);

	}

}
