package aaa.cubeup.cube.room.views;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Ignore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.data.nonentities.BaseBVable;

import static aaa.cubeup.cube.data.nonentities.Cattype.ITEM;

@JsonPropertyOrder({"item_name", "item_id"})
@DatabaseView(value = "SELECT catalog_object_id AS item_id, name AS item_name FROM entity_item ORDER BY name", viewName = "bv_item")
public class ItemBV implements BaseBVable, Serializable {

	@ColumnInfo(name = "item_name") @JsonProperty("item_name")
	public String itemName;
	@ColumnInfo(name = "item_id") @JsonProperty("item_id")
	public String itemId;

	public ItemBV() { }

	@Ignore
	public ItemBV(String itemId, String itemName) {
		this.itemId = itemId;
		this.itemName = itemName;
	}

	@NotNull public String getName() {
		return this.itemName;
	}

	@NotNull public String findId() {
		return this.itemId;
	}
	@NotNull @JsonIgnore
	public Cattype cattype() {
		return ITEM;
	}
	@JsonIgnore
	public String rightLabel() {
		return "";
	}

	@NotNull @Override public String findName() {
		return itemName;
	}

}
