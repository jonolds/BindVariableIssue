package aaa.cubeup.cube.room.entities.order;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.room.*;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.*;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import aaa.cubeup.cube.data.nonentities.OrderMoneyAmountsZZZZ;


@JsonPropertyOrder(
	{"name", "id", "location_id", "reference_id", "name", "created_at", "updated_at", "closed_at", "state", "version", "source", "customer_id",
		"line_items", "taxes", "discounts", "service_charges", "fulfillments", "returns", "return_amounts", "net_amounts", "rounding_adjustment",
		"tenders", "refunds", "metadata", "total_money", "total_tax_money", "total_discount_money", "total_service_charge_money", "orderDiscs",
		"orderTaxes"})
@Entity(tableName = "order_object")
public class OrderObjectEntity {

	/* Fields */
	public String name;
	@ColumnInfo(name = "order_id") @JsonProperty("id") @JsonAlias("order_id")
	public String orderId;
	@PrimaryKey @NonNull @ColumnInfo(name = "reference_id") @JsonProperty("reference_id")
	public String referenceId;
	@ColumnInfo(name = "location_id") @JsonProperty("location_id")
	public String locationId;
	@ColumnInfo(name = "metadata")
	public Map<String, String> metadata = new ArrayMap<>();
	@ColumnInfo(name = "created_at") @JsonProperty("created_at")
	public String createdAt;
	@ColumnInfo(name = "updated_at") @JsonProperty("updated_at")
	public String updatedAt;
	@ColumnInfo(name = "closed_at") @JsonProperty("closed_at")
	public String closedAt;
	public String state;
	public Integer version;
	@Embedded(prefix = "source_")
	public OrderSource source;
	@ColumnInfo(name = "customer_id") @JsonProperty("customer_id")
	public String customerId;

	@ColumnInfo(name = "service_charges") @JsonProperty("service_charges")
	public List<OrderServiceCharge> serviceCharges;
	public List<OrderFulfillment> fulfillments;
	public List<OrderReturn> returns;
	@Embedded(prefix = "return_") @JsonProperty("return_amounts")
	public OrderMoneyAmountsZZZZ returnAmounts;
	@Embedded(prefix = "net_") @JsonProperty("net_amounts")
	public OrderMoneyAmountsZZZZ netAmounts;
	@JsonProperty("rounding_adjustment")
	public OrderRoundingAdjustment roundingAdjustment;
	public List<Tender> tenders;
	public List<Refund> refunds;
	@Embedded(prefix = "total_") @JsonProperty("total_money")
	public Money totalMoney = new Money(0L, "USD");
	@Embedded(prefix = "total_tax_") @JsonProperty("total_tax_money")
	public Money totalTaxMoney = new Money(0L, "USD");
	@Embedded(prefix = "total_discount_") @JsonProperty("total_discount_money")
	public Money totalDiscountMoney = new Money(0L, "USD");
	@Embedded(prefix = "total_service_charge_") @JsonProperty("total_service_charge_money")
	public Money totalServiceChargeMoney = new Money(0L, "USD");

	@JsonCreator
	public OrderObjectEntity(
		@JsonProperty("name") String name,
		@JsonProperty("location_id") String locationId,
		@JsonProperty("id") String orderId,
		@NotNull @JsonProperty("reference_id") String referenceId,
		@JsonProperty("source") OrderSource source,
		@JsonProperty("customer_id") String customerId,
		@JsonProperty("service_charges") List<OrderServiceCharge> serviceCharges,
		@JsonProperty("fulfillments") List<OrderFulfillment> fulfillments,
		@JsonProperty("returns") List<OrderReturn> returns,
		@JsonProperty("return_amounts") OrderMoneyAmountsZZZZ returnAmounts,
		@JsonProperty("net_amounts") OrderMoneyAmountsZZZZ netAmounts,
		@JsonProperty("rounding_adjustment") OrderRoundingAdjustment roundingAdjustment,
		@JsonProperty("tenders") List<Tender> tenders,
		@JsonProperty("refunds") List<Refund> refunds,
		@JsonProperty("metadata") Map<String, String> metadata,
		@JsonProperty("created_at") String createdAt,
		@JsonProperty("updated_at") String updatedAt,
		@JsonProperty("closed_at") String closedAt,
		@JsonProperty("state") String state,
		@JsonProperty("version") Integer version,
		@JsonProperty("total_money") Money totalMoney,
		@JsonProperty("total_tax_money") Money totalTaxMoney,
		@JsonProperty("total_discount_money") Money totalDiscountMoney,
		@JsonProperty("total_service_charge_money") Money totalServiceChargeMoney) {

		this.metadata = metadata;
//		this.metadata.putAll(metadata);
		this.name = name;
		this.orderId = orderId;
		this.locationId = locationId;
		this.referenceId = referenceId;
		this.source = source;
		this.customerId = customerId;
		this.serviceCharges = serviceCharges;
		this.fulfillments = fulfillments;
		this.returns = returns;
		this.returnAmounts = returnAmounts;
		this.netAmounts = netAmounts;
		this.roundingAdjustment = roundingAdjustment;
		this.tenders = tenders;
		this.refunds = refunds;

		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.closedAt = closedAt;
		this.state = state;
		this.version = version;
		this.totalMoney = totalMoney;
		this.totalTaxMoney = totalTaxMoney;
		this.totalDiscountMoney = totalDiscountMoney;
		this.totalServiceChargeMoney = totalServiceChargeMoney;
		this.unpackMeta();
	}

	private void unpackMeta() {
		if (metadata != null) {
			this.name = metadata.getOrDefault("name", "DEFAULT");

		}
	}

	@Ignore
	public OrderObjectEntity(@NotNull String referenceId, String name) {
		this.name = name;
		this.referenceId = referenceId;
	}


}