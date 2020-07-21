package aaa.cubeup.cube.ui.dashboardfrags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import aaa.cubeup.cube.R.id;
import aaa.cubeup.cube.R.layout;
import aaa.cubeup.cube.data.nonentities.OrderButton;
import aaa.cubeup.cube.ui.viewmodels.ZZZZVM;


public class RightOrdersFrag extends Fragment {

	RightOrderListAdapter adapter;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(layout.frag_right_list, container, false);
		adapter = new RightOrderListAdapter();
		return view;
	}

	public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		RecyclerView recyclerView = view.findViewById(id.recyclerview_ticket);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		recyclerView.setAdapter(adapter);


		new ViewModelProvider(requireActivity()).get(ZZZZVM.class).repo.getOrderButtonViewsLV().observe(getViewLifecycleOwner(), new Observer<List<OrderButton>>() {
			@Override
			public void onChanged(@Nullable final List<OrderButton> orders) {
				if (orders != null) {
					adapter.setOrderButtons(orders);
				}
			}
		});
	}

}