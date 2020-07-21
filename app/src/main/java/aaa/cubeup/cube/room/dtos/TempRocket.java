package aaa.cubeup.cube.room.dtos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

import aaa.cubeup.cube.room.entities.catalog.TempModlistInfo;


@JsonPropertyOrder({"rocket_type", "reference_id", "line_item_uid", "quantity", "item_name", "item_id", "version", "vari_order_dtos", "infos", "disc_order_dtos",
					   "tax_order_dtos", "item_id"})
public class TempRocket {

	@ColumnInfo(name = "item_name") @JsonProperty("item_name")
	public String itemName;

	@ColumnInfo(name = "item_id") @JsonProperty("item_id")
	public String itemId;

	@ColumnInfo(name = "quantity") @JsonProperty("quantity")
	public String quantity;

	@ColumnInfo(name = "version") @JsonProperty("version")
	public Integer version;

	@ColumnInfo(name = "reference_id") @JsonProperty("reference_id")
	public String referenceId;

	@PrimaryKey @NonNull @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	public String lineItemUid = "";

	@ColumnInfo(name = "rocket_type") @JsonProperty("rocket_type")
	public String rocketType;



	@Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid") @JsonProperty("vari_order_dtos")
	public List<TempVariDTO> variOrderDTOs;

	@Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid") @JsonProperty("infos")
	public List<TempModlistInfo> infos;

	@Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid") @JsonProperty("disc_order_dtos")
	public List<TempDiscountDTO> discountOrderDTOs;

	@Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid") @JsonProperty("tax_order_dtos")
	public List<TempTaxDTO> taxOrderDTOs;


}
