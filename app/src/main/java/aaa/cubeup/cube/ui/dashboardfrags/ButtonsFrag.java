package aaa.cubeup.cube.ui.dashboardfrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import java.util.Objects;

import aaa.cubeup.cube.R.id;
import aaa.cubeup.cube.R.layout;
import aaa.cubeup.cube.ui.viewmodels.ZZZZVM;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ButtonsFrag extends Fragment {

	private ZZZZVM cubeVM;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cubeVM = new ViewModelProvider(requireActivity()).get(ZZZZVM.class);
	}

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(layout.frag_buttons, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ZZZZVM.columnCount = getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE ? 5 : 4;

		RecyclerView recyclerView = view.findViewById(id.recyclerview_button_grid);
		((SimpleItemAnimator) Objects.requireNonNull(recyclerView.getItemAnimator())).setSupportsChangeAnimations(false);
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemViewCacheSize(5);
		ButtonsAdapter adapter = new ButtonsAdapter();
		GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), ZZZZVM.columnCount);
		recyclerView.setLayoutManager(gridLayoutManager);
		recyclerView.setAdapter(adapter);

		cubeVM.getButtonWZZZZsLV().observe(requireActivity(), buttonConfigs -> {
			if (buttonConfigs != null) {
				adapter.setButtonWZZZZs(ZZZZVM.getRightSizedCatalogButtonsList(buttonConfigs));
			}
		});
	}

}

