package aaa.cubeup.cube.ui.dashboardfrags;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import aaa.cubeup.cube.R.id;
import aaa.cubeup.cube.R.layout;
import aaa.cubeup.cube.data.nonentities.OrderButton;
import aaa.cubeup.cube.ui.dashboardfrags.RightOrderListAdapter.PanelRightViewHolder;

public class RightOrderListAdapter extends Adapter<PanelRightViewHolder> {

	private List<OrderButton> orderButtons = new ArrayList<>();
	public void setOrderButtons(List<OrderButton> orderButtons) {
		this.orderButtons = orderButtons;
		notifyDataSetChanged();
	}

	@Override @NotNull
	public PanelRightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(layout.element_orders_list, parent, false);
		return new PanelRightViewHolder(view);

	}
	@Override
	public void onBindViewHolder(@NotNull PanelRightViewHolder holder, int position) {
		if (orderButtons != null) {
			String buttonText = position + " - " + orderButtons.get(position).name;
			holder.rightPanelnameList_TextView.setText(buttonText);
		}

	}
	@Override
	public int getItemCount() {
		return orderButtons != null ? orderButtons.size() : 0;
	}

	static public class PanelRightViewHolder extends ViewHolder implements OnClickListener {
		public final TextView rightPanelnameList_TextView;

		public PanelRightViewHolder(View view) {
			super(view);
			this.rightPanelnameList_TextView = view.findViewById(id.recyclerViewItem_TextView);
		}

		@NotNull
		@Override
		public String toString() {
			return super.toString() + " '" + rightPanelnameList_TextView.getText() + "'";
		}

		@Override
		public void onClick(View v) {

		}
	}

}
