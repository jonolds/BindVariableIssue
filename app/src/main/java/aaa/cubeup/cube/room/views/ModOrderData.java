package aaa.cubeup.cube.room.views;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Ignore;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItemModifier;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@JsonPropertyOrder({"name", "catalog_object_id", "ordinal", "modifier_list_id", "base_price_money"})
@DatabaseView(value = "SELECT " + "name, " + "catalog_object_id, " + "amount AS base_price_amount, " + "currency AS base_price_currency, " + "modifier_list_id, " + "ordinal " + "FROM entity_mod " + "ORDER BY ordinal", viewName = "data_order_mod")
public class ModOrderData {

	public String name;
	@ColumnInfo(name = "catalog_object_id")
	@JsonProperty("catalog_object_id")
	public String catalogObjectId;
	@Embedded(prefix = "base_price_")
	@JsonProperty("base_price_money")
	public Money basePriceMoney;
	@ColumnInfo(name = "modifier_list_id")
	@JsonProperty("modifier_list_id")
	public String modifierListId;
	public Integer ordinal;

	public ModOrderData() {
	}

	@Ignore
	public ModOrderData(String name, String catalogObjectId, Money basePriceMoney, String modifierListId, Integer ordinal) {
		this.name = name;
		this.catalogObjectId = catalogObjectId;
		this.basePriceMoney = basePriceMoney;
		this.modifierListId = modifierListId;
		this.ordinal = ordinal;
	}

	public ModOrderData(OrderLineItemModifier orderLineItemModifier) {
		this.catalogObjectId = orderLineItemModifier.getCatalogObjectId();
		this.name = orderLineItemModifier.getName();
		this.basePriceMoney = orderLineItemModifier.getBasePriceMoney();
	}
	@Override
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof ModOrderData)) {
			return false;
		}
		ModOrderData other = (ModOrderData) o;
		return Objects.equals(catalogObjectId, other.catalogObjectId) && Objects.equals(name, other.name) && Objects.equals(modifierListId, other.modifierListId) && Objects.equals(basePriceMoney, other.basePriceMoney);
	}
	@Override
	@NotNull
	public String toString() {
		return "name: " + name + "  catalogObjectId: " + catalogObjectId + "  modifierListId: " + modifierListId + "  ordinal: " + ordinal + "  priceMoney: " + basePriceMoney;
	}

}
