package aaa.cubeup.cube.room.entities.otherentities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import org.jetbrains.annotations.NotNull;

@JsonPropertyOrder({"item_id", "item_name", "line_item_uid", "reference_id", "quantity", "version", "rocket_type"})
@Entity(tableName = "temp_ids")
public class TempId {

	@PrimaryKey @NonNull @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	public String lineItemUid;
	@ColumnInfo(name = "reference_id") @JsonProperty("reference_id")
	public String referenceId;
	@ColumnInfo(name = "item_id") @JsonProperty("item_id")
	public String itemId;
	@ColumnInfo(name = "item_name") @JsonProperty("item_name")
	public String itemName;

	public String quantity;

	public Integer version;

	@ColumnInfo(name = "rocket_type") @JsonProperty("rocket_type")
	public String rocketType;


	public TempId(@NotNull String itemId, String itemName, @NotNull String lineItemUid, String referenceId, String quantity, Integer version, String rocketType) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.lineItemUid = lineItemUid;
		this.referenceId = referenceId;
		this.quantity = quantity;
		this.version = version;
		this.rocketType = rocketType;
	}
}

