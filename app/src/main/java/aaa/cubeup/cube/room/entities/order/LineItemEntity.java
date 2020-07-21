package aaa.cubeup.cube.room.entities.order;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.room.*;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.Money;
import com.squareup.square.models.OrderQuantityUnit;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

import static androidx.room.ForeignKey.CASCADE;


@JsonPropertyOrder({"line_item_uid", "reference_id", "name", "item_id", "quantity", "quantity_unit", "note", "catalog_object_id", "variation_name",
	"metadata", "modifiers", "applied_taxes", "applied_discounts", "base_price_money", "variation_total_price_money", "gross_sales_money",
	"total_tax_money", "total_discount_money", "total_money", "applied_discs_set", "mods_set", "applied_taxes_set"})
@Entity(tableName = "order_line_item", indices = {@Index("reference_id"),
	@Index("catalog_object_id")}, foreignKeys = @ForeignKey(entity = OrderObjectEntity.class, parentColumns = "reference_id", childColumns = "reference_id", onDelete = CASCADE, deferred = true))
public class LineItemEntity {

	/* Fields */
	@PrimaryKey @NonNull @ColumnInfo(name = "line_item_uid") @JsonProperty("line_item_uid")
	public String lineItemUid;
	@ColumnInfo(name = "reference_id") @JsonProperty("reference_id")
	public String referenceId;
	public String name;
	@ColumnInfo(name = "item_id") @JsonProperty("item_id")
	public String itemId;
	@ColumnInfo(name = "variation_name") @JsonProperty("variation_name")
	public String variationName;
	@ColumnInfo(name = "catalog_object_id") @JsonProperty("catalog_object_id")
	public String catalogObjectId;  // Variation id, NOT Item id
	public String quantity = "0";
	@ColumnInfo(name = "quantity_unit") @JsonProperty("quantity_unit")
	public OrderQuantityUnit quantityUnit;
	public String note;
	public Map<String, String> metadata = new ArrayMap<>();

	@Embedded(prefix = "base_price_") @JsonProperty("base_price_money")
	public Money basePriceMoney = new Money(0L, "USD");
	@Embedded(prefix = "variation_total_price_") @JsonProperty("variation_total_price_money")
	public Money variationTotalPriceMoney = new Money(0L, "USD");
	@Embedded(prefix = "gross_sales_") @JsonProperty("gross_sales_money")
	public Money grossSalesMoney = new Money(0L, "USD");
	@Embedded(prefix = "total_tax_") @JsonProperty("total_tax_money")
	public Money totalTaxMoney = new Money(0L, "USD");
	@Embedded(prefix = "total_discount_") @JsonProperty("total_discount_money")
	public Money totalDiscountMoney = new Money(0L, "USD");
	@Embedded(prefix = "total_") @JsonProperty("total_money")
	public Money totalMoney = new Money(0L, "USD");

	@Ignore
	public LineItemEntity(@NotNull String lineItemUid) {
		this.lineItemUid = lineItemUid;
	}

	@JsonCreator
	public LineItemEntity(@JsonProperty("line_item_uid") @NonNull String lineItemUid,
		@JsonProperty("reference_id") String referenceId,
		@JsonProperty("name") String name,
		@JsonProperty("item_id") String itemId,
		@JsonProperty("quantity") String quantity,
		@JsonProperty("quantity_unit") OrderQuantityUnit quantityUnit,
		@JsonProperty("note") String note,
		@JsonProperty("catalog_object_id") String catalogObjectId,
		@JsonProperty("variation_name") String variationName,
		@JsonProperty("metadata") Map<String, String> metadata,
		@JsonProperty("base_price_money") Money basePriceMoney,
		@JsonProperty("variation_total_price_money") Money variationTotalPriceMoney,
		@JsonProperty("gross_sales_money") Money grossSalesMoney,
		@JsonProperty("total_tax_money") Money totalTaxMoney,
		@JsonProperty("total_discount_money") Money totalDiscountMoney,
		@JsonProperty("total_money") Money totalMoney) {
		this.metadata = metadata;
		this.lineItemUid = lineItemUid;
		this.referenceId = referenceId;
		this.name = name;
		this.itemId = itemId;
		this.quantity = quantity;
		this.quantityUnit = quantityUnit;
		this.note = note;
		this.catalogObjectId = catalogObjectId;
		this.variationName = variationName;

		this.basePriceMoney = basePriceMoney;
		this.variationTotalPriceMoney = variationTotalPriceMoney;
		this.grossSalesMoney = grossSalesMoney;
		this.totalTaxMoney = totalTaxMoney;
		this.totalDiscountMoney = totalDiscountMoney;
		this.totalMoney = totalMoney;
		unpackMeta();
	}

	private void unpackMeta() {
		if (metadata != null) {
			this.itemId = metadata.getOrDefault("itemId", null);
			this.referenceId = metadata.getOrDefault("referenceId", null);
		}
	}

}

