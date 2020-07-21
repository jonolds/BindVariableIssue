package aaa.cubeup.cube.data.order;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.room.DatabaseView;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderLineItem;
import com.squareup.square.models.OrderQuantityUnit;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import aaa.cubeup.cube.room.dtos.TempVariDTO;
import aaa.cubeup.cube.room.entities.order.LineItemEntity;
import aaa.cubeup.cube.room.entities.order.LineItemModEntity;
import aaa.cubeup.cube.room.views.AppliedDiscount;
import aaa.cubeup.cube.room.views.AppliedTax;
import aaa.cubeup.cube.utils.LiCalc;

import static java.util.stream.Collectors.toList;


@JsonPropertyOrder(
	{"line_item_uid", "reference_id", "name", "quantity", "quantity_unit", "note", "catalog_object_id", "variation_name", "metadata", "modifiers",
		"applied_taxes", "applied_discounts", "base_price_money", "variation_total_price_money", "gross_sales_money", "total_tax_money",
		"total_discount_money", "total_money", "applied_discs_set", "mods_set", "applied_taxes_set"})
@DatabaseView(value = "SELECT * from order_line_item", viewName = "cube_line_item")
public class LineItemZZZZ extends LineItemEntity {

	@JsonProperty("applied_taxes") @Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid")
	public List<AppliedTax> appliedTaxes;

	@JsonProperty("applied_discounts") @Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid")
	public List<AppliedDiscount> appliedDiscounts;

	@JsonProperty("modifiers") @Relation(parentColumn = "line_item_uid", entityColumn = "line_item_uid", entity = LineItemModEntity.class)
	public List<LineItemMod> modifiers;

	@JsonCreator
	public LineItemZZZZ(
		@JsonProperty("line_item_uid") @NonNull String lineItemUid,
		@JsonProperty("reference_id") String referenceId,
		@JsonProperty("name") String name,
		@JsonProperty("item_id") String itemId,
		@JsonProperty("variation_name") String variationName,
		@JsonProperty("metadata") Map<String, String> metadata,
		@JsonProperty("catalog_object_id") String catalogObjectId,
		@JsonProperty("quantity") String quantity,
		@JsonProperty("quantity_unit") OrderQuantityUnit quantityUnit,
		@JsonProperty("note") String note,
		@JsonProperty("modifiers") List<LineItemMod> modifiers,
		@JsonProperty("applied_taxes") List<AppliedTax> appliedTaxes,
		@JsonProperty("applied_discounts") List<AppliedDiscount> appliedDiscounts,
		@JsonProperty("base_price_money") Money basePriceMoney,
		@JsonProperty("variation_total_price_money") Money variationTotalPriceMoney,
		@JsonProperty("gross_sales_money") Money grossSalesMoney,
		@JsonProperty("total_tax_money") Money totalTaxMoney,
		@JsonProperty("total_discount_money") Money totalDiscountMoney,
		@JsonProperty("total_money") Money totalMoney) {
		super(lineItemUid, referenceId, name, itemId, quantity, quantityUnit, note, catalogObjectId, variationName, metadata, basePriceMoney, variationTotalPriceMoney, grossSalesMoney, totalTaxMoney, totalDiscountMoney, totalMoney);
		this.modifiers = modifiers;
		this.appliedTaxes = appliedTaxes;
		this.appliedDiscounts = appliedDiscounts;
	}

	@Ignore @JsonIgnore
//	public LineItemZZZZ(@NotNull String lineItemUid, String referenceId, String itemId, VariOrderDTO variOrderDTO, double quantity, List<LineItemMod> modifiers, List<AppliedTax> appliedTaxes, List<AppliedDiscount> appliedDiscounts) {
	public LineItemZZZZ(
		@NotNull String lineItemUid,
		String referenceId,
		String itemId,
		TempVariDTO tempVariDTO,
		double quantity,
		List<LineItemMod> modifiers,
		List<AppliedTax> appliedTaxes,
		List<AppliedDiscount> appliedDiscounts)
	{
		super(lineItemUid);
		this.metadata = new ArrayMap<>();
		this.referenceId = addToMeta("referenceId", referenceId);
		this.name = tempVariDTO.itemName;
		this.itemId = addToMeta("itemId", itemId);
		this.quantity = String.valueOf(quantity);
		this.catalogObjectId = tempVariDTO.findId();
		this.variationName = tempVariDTO.variationName;
		this.appliedTaxes = appliedTaxes;
		this.appliedDiscounts = appliedDiscounts;
		this.modifiers = LiCalc.setModValues(modifiers, quantity);
	}

	public String addToMeta(String key, String value) {
		return this.metadata.compute(key, (k, v) -> value);
	}

	@Ignore @JsonIgnore
	public LineItemZZZZ(OrderLineItem from, String referenceId) {
		super(from.getUid());
		this.metadata = from.getMetadata().entrySet().stream().collect(Collectors.toMap(entry->entry.getKey(), entry ->entry.getValue()));
		this.referenceId = addToMeta("referenceId", referenceId);
		this.name = from.getName();
		this.itemId = Optional.ofNullable(from.getMetadata()).map(meta -> meta.get("itemId")).orElse(null);
		this.quantity = from.getQuantity();
		this.quantityUnit = from.getQuantityUnit();
		this.note = from.getNote();
		this.catalogObjectId = from.getCatalogObjectId();
		this.variationName = from.getVariationName();
		this.modifiers =
			Optional.ofNullable(from.getModifiers()).map(mods -> mods.stream().map(mod -> new LineItemMod(mod, lineItemUid)).collect(toList()))
				.orElse(null);
		this.appliedTaxes = Optional.ofNullable(from.getAppliedTaxes())
								.map(applieds -> applieds.stream().map(appTax -> new AppliedTax(appTax, from.getUid())).collect(toList()))
								.orElse(null);
		this.appliedDiscounts = Optional.ofNullable(from.getAppliedDiscounts())
									.map(applieds -> applieds.stream().map(appDisc -> new AppliedDiscount(appDisc, from.getUid())).collect(toList()))
									.orElse(null);

		setBasePriceMoney(from.getBasePriceMoney());
		this.variationTotalPriceMoney = from.getVariationTotalPriceMoney();
		this.grossSalesMoney = from.getGrossSalesMoney();
		this.totalTaxMoney = from.getTotalTaxMoney();
		this.totalDiscountMoney = from.getTotalDiscountMoney();
		this.totalMoney = from.getTotalMoney();
	}

	public void setBasePriceMoney(Money basePriceMoney) {
		this.basePriceMoney = basePriceMoney;
		calculateVariationTotalPriceMoney(Double.parseDouble(quantity), basePriceMoney.getAmount());
	}

	public void calculateVariationTotalPriceMoney(double qty, Long baseAmount) {
		this.variationTotalPriceMoney = new Money((long) (baseAmount * qty), "USD");
	}

}
