package aaa.cubeup.cube.data.order;

import androidx.annotation.StringDef;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.squareup.square.models.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import aaa.cubeup.cube.data.nonentities.OrderMoneyAmountsZZZZ;
import aaa.cubeup.cube.room.entities.order.LineItemDiscountEntity;
import aaa.cubeup.cube.room.entities.order.LineItemTaxEntity;
import aaa.cubeup.cube.room.entities.order.OrderObjectEntity;


@JsonPropertyOrder(
	{"id", "location_id", "reference_id", "name", "created_at", "updated_at", "closed_at", "state", "version", "source", "customer_id", "line_items",
		"taxes", "discounts", "service_charges", "fulfillments", "returns", "return_amounts", "net_amounts", "rounding_adjustment", "tenders",
		"refunds", "metadata", "total_money", "total_tax_money", "total_discount_money", "total_service_charge_money", "orderDiscs", "orderTaxes"})
public class OrderZZZZ extends OrderObjectEntity {

	@JsonProperty("line_items") @Relation(parentColumn = "reference_id", entityColumn = "reference_id")
	public List<LineItemZZZZ> lineItems;
	@JsonProperty("taxes") @Relation(parentColumn = "reference_id", entityColumn = "reference_id", entity = LineItemTaxEntity.class)
	public List<LineItemTax> taxes;
	@JsonProperty("discounts") @Relation(parentColumn = "reference_id", entityColumn = "reference_id", entity = LineItemDiscountEntity.class)
	public List<LineItemDiscount> discounts;

	@Ignore @JsonIgnore
	public OrderZZZZ(String referenceId, String name) {
		super(referenceId, name);
		this.lineItems = new ArrayList<>();
		this.taxes = new ArrayList<>();
		this.discounts = new ArrayList<>();
	}

	@JsonCreator
	public OrderZZZZ(
		@JsonProperty("name") String name,
		@JsonProperty("location_id") String locationId,
		@JsonProperty("id") String orderId,
		@JsonProperty("reference_id") String referenceId,
		@JsonProperty("source") OrderSource source,
		@JsonProperty("customer_id") String customerId,
		@JsonProperty("line_items") List<LineItemZZZZ> lineItems,
		@JsonProperty("taxes") List<LineItemTax> taxes,
		@JsonProperty("discounts") List<LineItemDiscount> discounts,
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
		super(name,
			locationId,
			orderId,
			referenceId,
			source,
			customerId,
			serviceCharges,
			fulfillments,
			returns,
			returnAmounts,
			netAmounts,
			roundingAdjustment,
			tenders,
			refunds,
			metadata,
			createdAt,
			updatedAt,
			closedAt,
			state,
			version,
			totalMoney,
			totalTaxMoney,
			totalDiscountMoney,
			totalServiceChargeMoney);
		this.lineItems = lineItems;
		this.taxes = taxes;
		this.discounts = discounts;

	}


	public static class Scope {

		public static final String SCOPE_ORDER = "ORDER", SCOPE_LINE_ITEM = "LINE_ITEM";


		@StringDef({SCOPE_ORDER, SCOPE_LINE_ITEM}) @Retention(RetentionPolicy.SOURCE)
		public @interface ScopeDef {
		}

	}

}
