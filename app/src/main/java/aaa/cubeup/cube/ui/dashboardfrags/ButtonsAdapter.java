package aaa.cubeup.cube.ui.dashboardfrags;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import aaa.cubeup.cube.R.id;
import aaa.cubeup.cube.R.layout;
import aaa.cubeup.cube.data.nonentities.CatalogButtonWZZZZ;
import aaa.cubeup.cube.ui.dashboardfrags.ButtonsAdapter.ButtonConfigViewHolder;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class ButtonsAdapter extends Adapter<ButtonConfigViewHolder> {

	public static int buttonsHtLand = 0, buttonsHtPort = 0;
	private List<CatalogButtonWZZZZ> buttonWZZZZs = new ArrayList<>();
	public void setButtonWZZZZs(List<CatalogButtonWZZZZ> buttonWZZZZs) {
		this.buttonWZZZZs = buttonWZZZZs;
		notifyDataSetChanged();
	}

	public ButtonsAdapter() {
	}
	@Override
	@NotNull
	public ButtonConfigViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(layout.buttons_element, parent, false);

		Button button = itemView.findViewById(id.recyclerViewItem_button);

		button.setHeight(getMaxHeight(parent));

		ButtonConfigViewHolder viewHolder = new ButtonConfigViewHolder(itemView);

		return viewHolder;
	}
	private static Integer getMaxHeight(ViewGroup parent) {
		if (parent.getContext().getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE)
			return (buttonsHtLand != 0 ? buttonsHtLand : (buttonsHtLand = parent.getHeight())) / 5;
		return (buttonsHtPort != 0 ? buttonsHtPort : (buttonsHtPort = parent.getHeight())) / 5;
	}
	@Override
	public void onBindViewHolder(@NotNull ButtonConfigViewHolder holder, int position) {
		if (buttonWZZZZs != null) holder.set(buttonWZZZZs.get(position));
	}
	@Override
	public int getItemCount() {
		return buttonWZZZZs != null ? buttonWZZZZs.size() : 0;
	}


	static public class ButtonConfigViewHolder extends ViewHolder {
		public Button button;

		public ButtonConfigViewHolder(View view) {
			super(view);
			this.button = view.findViewById(id.recyclerViewItem_button);
			button.setStateListAnimator(null);
			button.clearAnimation();
			button.animate().cancel();
			view.animate().cancel();
		}

		public void set(CatalogButtonWZZZZ buttonConfig) {
			button.setText(buttonConfig.getButtonText());
		}

		@Override @NotNull
		public String toString() {
			return super.toString() + " '" + button.getText() + "'";
		}
	}
}