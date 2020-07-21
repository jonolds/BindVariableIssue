package aaa.cubeup.cube.room.views;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Embedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;

import java.util.Objects;


@JsonPropertyOrder({"variation_name", "id", "item_id", "amount", "ordinal", "cattype"})
@DatabaseView(value = "SELECT " +

	"		name AS variation_name, " + "		catalog_object_id, " + "		(SELECT name FROM entity_item WHERE catalog_object_id IS item_id) AS item_name, " + "		item_id, " + "		amount AS base_price_amount, " + "		currency AS base_price_currency, " + "		ordinal " + "	FROM entity_vari " + "	ORDER BY ordinal", viewName = "bv_vari")
public class VariBV {

	@ColumnInfo(name = "variation_name") @JsonProperty("variation_name")
	public String variationName;
	@ColumnInfo(name = "catalog_object_id") @JsonProperty("catalog_object_id")
	public String catalogObjectId;
	@ColumnInfo(name = "item_name") @JsonProperty("item_name")
	public String itemName;
	@ColumnInfo(name = "item_id") @JsonProperty("item_id")
	public String itemId;

	@Embedded(prefix = "base_price_") @JsonProperty("price_money")
	public Money basePriceMoney;
	public Integer ordinal;


	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof VariBV)) {
			return false;
		}
		VariBV other = (VariBV) o;
		return Objects.equals(catalogObjectId, other.catalogObjectId) && Objects.equals(variationName, other.variationName) && Objects.equals(basePriceMoney, other.basePriceMoney) && Objects.equals(itemId, other.itemId) && Objects.equals(ordinal, other.ordinal);
	}

}
