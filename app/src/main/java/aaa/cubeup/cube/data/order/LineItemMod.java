package aaa.cubeup.cube.data.order;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemModifier;

import org.jetbrains.annotations.NotNull;

import aaa.cubeup.cube.data.nonentities.Cattype;
import aaa.cubeup.cube.data.nonentities.BaseBVable;
import aaa.cubeup.cube.room.entities.order.LineItemModEntity;

import static aaa.cubeup.cube.data.nonentities.Cattype.MOD;
import static aaa.cubeup.cube.utils.MoneyHelp.toDollarsString;

public class LineItemMod extends LineItemModEntity implements BaseBVable {

	@JsonCreator
	public LineItemMod(
		@NotNull @JsonProperty("applied_uid") String appliedUid,
		@JsonProperty("catalog_object_id") String catalogObjectId,
		@JsonProperty("name") String name,
		@JsonProperty("base_price_money") Money basePriceMoney,
		@JsonProperty("total_price_money") Money totalPriceMoney,
		@NonNull @JsonProperty("line_item_uid") String lineItemUid) {
		super(appliedUid, catalogObjectId, name, basePriceMoney, totalPriceMoney, lineItemUid);
	}

	public LineItemMod(OrderLineItemModifier from, String lineItemUid) {
		super(from, lineItemUid);
	}


	@NotNull @Override
	public String findId() {
		return catalogObjectId;
	}
	@NotNull @Override
	public Cattype cattype() {
		return MOD;
	}
	@Override
	public String rightLabel() {
		return toDollarsString(basePriceMoney);
	}

	@NotNull @Override public String findName() {
		return name;
	}


}
